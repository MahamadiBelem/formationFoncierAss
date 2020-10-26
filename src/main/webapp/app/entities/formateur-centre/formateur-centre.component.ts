import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormateurCentre } from 'app/shared/model/formateur-centre.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FormateurCentreService } from './formateur-centre.service';
import { FormateurCentreDeleteDialogComponent } from './formateur-centre-delete-dialog.component';
import { SaveFormateurCentreComponent } from './save-formateur-centre/save-formateur-centre.component';

@Component({
  selector: 'jhi-formateur-centre',
  templateUrl: './formateur-centre.component.html',
})
export class FormateurCentreComponent implements OnInit, OnDestroy {
  formateurCentres?: IFormateurCentre[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected formateurCentreService: FormateurCentreService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.formateurCentreService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IFormateurCentre[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInFormateurCentres();
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

  trackId(index: number, item: IFormateurCentre): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFormateurCentres(): void {
    this.eventSubscriber = this.eventManager.subscribe('formateurCentreListModification', () => this.loadPage());
  }

  delete(formateurCentre: IFormateurCentre): void {
    const modalRef = this.modalService.open(FormateurCentreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formateurCentre = formateurCentre;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IFormateurCentre[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/formateur-centre'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.formateurCentres = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  savemodal(): void {
    const savemodale = this.modalService.open(SaveFormateurCentreComponent, { size: 'lg', backdrop: 'static' });
  }
  updatemodal(formateurCentre: IFormateurCentre): void {
    const updatemodale = this.modalService.open(SaveFormateurCentreComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.formateurCentre = formateurCentre;
  }
}
