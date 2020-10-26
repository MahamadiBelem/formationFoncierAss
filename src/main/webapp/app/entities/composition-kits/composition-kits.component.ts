import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompositionKits } from 'app/shared/model/composition-kits.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CompositionKitsService } from './composition-kits.service';
import { CompositionKitsDeleteDialogComponent } from './composition-kits-delete-dialog.component';
import { SaveCompositionKitsComponent } from './save-composition-kits/save-composition-kits.component';
import { UpdateCompositionKitsComponent } from './update-composition-kits/update-composition-kits.component';

@Component({
  selector: 'jhi-composition-kits',
  templateUrl: './composition-kits.component.html',
})
export class CompositionKitsComponent implements OnInit, OnDestroy {
  compositionKits?: ICompositionKits[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected compositionKitsService: CompositionKitsService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.compositionKitsService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<ICompositionKits[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInCompositionKits();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICompositionKits): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCompositionKits(): void {
    this.eventSubscriber = this.eventManager.subscribe('compositionKitsListModification', () => this.loadPage());
  }

  delete(compositionKits: ICompositionKits): void {
    const modalRef = this.modalService.open(CompositionKitsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.compositionKits = compositionKits;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICompositionKits[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/composition-kits'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.compositionKits = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  savemodale(): void {
    const savemodal = this.modalService.open(SaveCompositionKitsComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodale(compositionKits: ICompositionKits): void {
    const updatemodal = this.modalService.open(UpdateCompositionKitsComponent, { size: 'lg', backdrop: 'static' });
    updatemodal.componentInstance.compositionKits = compositionKits;
  }
}
