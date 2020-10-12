import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVillages } from 'app/shared/model/villages.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VillagesService } from './villages.service';
import { VillagesDeleteDialogComponent } from './villages-delete-dialog.component';
import { SaveVillagesComponent } from './save-villages/save-villages.component';
import { ICommunes } from 'app/shared/model/communes.model';
import { CommunesService } from '../communes/communes.service';
import { UpdateVillagesComponent } from './update-villages/update-villages.component';

@Component({
  selector: 'jhi-villages',
  templateUrl: './villages.component.html',
})
export class VillagesComponent implements OnInit, OnDestroy {
  villages?: IVillages[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  communes?: ICommunes[];

  constructor(
    protected villagesService: VillagesService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private communesService: CommunesService
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.villagesService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IVillages[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInVillages();
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
      this.communesService.query().subscribe((res: HttpResponse<ICommunes[]>) => (this.communes = res.body || []));
    }
  }

  trackId(index: number, item: IVillages): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVillages(): void {
    this.eventSubscriber = this.eventManager.subscribe('villagesListModification', () => this.loadPage());
  }

  delete(villages: IVillages): void {
    const modalRef = this.modalService.open(VillagesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.villages = villages;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IVillages[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/villages'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.villages = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
  savemodal(): void {
    const savemodale = this.modalService.open(SaveVillagesComponent, { size: 'lg', backdrop: 'static' });
    savemodale.componentInstance.communes = this.communes;
  }

  updatemodal(village: IVillages): void {
    const updatemodale = this.modalService.open(UpdateVillagesComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.villages = village;
  }
}
