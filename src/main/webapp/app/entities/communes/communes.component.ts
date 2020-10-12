import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommunes } from 'app/shared/model/communes.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CommunesService } from './communes.service';
import { CommunesDeleteDialogComponent } from './communes-delete-dialog.component';
import { SaveCommunesComponent } from './save-communes/save-communes.component';
import { IProvinces } from 'app/shared/model/provinces.model';
import { ProvincesService } from '../provinces/provinces.service';
import { UpdateCommunesComponent } from './update-communes/update-communes.component';

@Component({
  selector: 'jhi-communes',
  templateUrl: './communes.component.html',
})
export class CommunesComponent implements OnInit, OnDestroy {
  communes?: ICommunes[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  provinces?: IProvinces[];
  constructor(
    protected communesService: CommunesService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private provinceService: ProvincesService
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.communesService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<ICommunes[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInCommunes();
    this.provinceService.query().subscribe((res: HttpResponse<IProvinces[]>) => (this.provinces = res.body || []));
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

  trackId(index: number, item: ICommunes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCommunes(): void {
    this.eventSubscriber = this.eventManager.subscribe('communesListModification', () => this.loadPage());
  }

  delete(communes: ICommunes): void {
    const modalRef = this.modalService.open(CommunesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.communes = communes;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICommunes[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/communes'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.communes = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  savemodal(): void {
    const refModal = this.modalService.open(SaveCommunesComponent, { size: 'lg', backdrop: 'static' });
    refModal.componentInstance.provinces = this.provinces;
  }

  updatemodal(communes: ICommunes): void {
    const updatemodale = this.modalService.open(UpdateCommunesComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.communes = communes;
  }
}
