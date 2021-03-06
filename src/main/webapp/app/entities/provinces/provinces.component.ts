import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProvinces } from 'app/shared/model/provinces.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ProvincesService } from './provinces.service';
import { ProvincesDeleteDialogComponent } from './provinces-delete-dialog.component';
import { SaveProvinceComponent } from './save-province/save-province.component';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from '../region/region.service';
import { UpdateProvincesComponent } from './update-provinces/update-provinces.component';

@Component({
  selector: 'jhi-provinces',
  templateUrl: './provinces.component.html',
})
export class ProvincesComponent implements OnInit, OnDestroy {
  provinces?: IProvinces[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  regions: IRegion[] = [];
  constructor(
    protected provincesService: ProvincesService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private regionService: RegionService
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.provincesService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IProvinces[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInProvinces();
    this.regionService.query().subscribe((res: HttpResponse<IRegion[]>) => (this.regions = res.body || []));
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

  trackId(index: number, item: IProvinces): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProvinces(): void {
    this.eventSubscriber = this.eventManager.subscribe('provincesListModification', () => this.loadPage());
  }

  delete(provinces: IProvinces): void {
    const modalRef = this.modalService.open(ProvincesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.provinces = provinces;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IProvinces[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/provinces'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.provinces = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  saveModal(): void {
    const refmodal = this.modalService.open(SaveProvinceComponent, { size: 'lg', backdrop: 'static' });
    refmodal.componentInstance.regions = this.regions;
  }
  upateModal(provinces: IProvinces): void {
    const updatemodal = this.modalService.open(UpdateProvincesComponent, { size: 'lg', backdrop: 'static' });
    updatemodal.componentInstance.provinces = provinces;
  }
}
