import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGestionnaire } from 'app/shared/model/gestionnaire.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GestionnaireService } from './gestionnaire.service';
import { GestionnaireDeleteDialogComponent } from './gestionnaire-delete-dialog.component';
import { SaveGestionnaireComponent } from './save-gestionnaire/save-gestionnaire.component';
import { UpdateGestionnaireComponent } from './update-gestionnaire/update-gestionnaire.component';

@Component({
  selector: 'jhi-gestionnaire',
  templateUrl: './gestionnaire.component.html',
})
export class GestionnaireComponent implements OnInit, OnDestroy {
  gestionnaires?: IGestionnaire[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected gestionnaireService: GestionnaireService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.gestionnaireService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IGestionnaire[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInGestionnaires();
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

  trackId(index: number, item: IGestionnaire): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGestionnaires(): void {
    this.eventSubscriber = this.eventManager.subscribe('gestionnaireListModification', () => this.loadPage());
  }

  delete(gestionnaire: IGestionnaire): void {
    const modalRef = this.modalService.open(GestionnaireDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gestionnaire = gestionnaire;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IGestionnaire[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/gestionnaire'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.gestionnaires = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  savemodal(): void {
    const savemodale = this.modalService.open(SaveGestionnaireComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodal(gestionnaire: IGestionnaire): void {
    const updatemodale = this.modalService.open(UpdateGestionnaireComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.gestionnaire = gestionnaire;
  }
}
