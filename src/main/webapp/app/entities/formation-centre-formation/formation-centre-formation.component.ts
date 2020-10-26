import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FormationCentreFormationService } from './formation-centre-formation.service';
import { FormationCentreFormationDeleteDialogComponent } from './formation-centre-formation-delete-dialog.component';
import { FormationCentreFormationUpdateComponent } from './formation-centre-formation-update.component';
import { SaveFormationCentreComponent } from './save-formation-centre/save-formation-centre.component';
import { UpdateFormationCentreComponent } from './update-formation-centre/update-formation-centre.component';

@Component({
  selector: 'jhi-formation-centre-formation',
  templateUrl: './formation-centre-formation.component.html',
})
export class FormationCentreFormationComponent implements OnInit, OnDestroy {
  formationCentreFormations?: IFormationCentreFormation[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected formationCentreFormationService: FormationCentreFormationService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.formationCentreFormationService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IFormationCentreFormation[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInFormationCentreFormations();
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

  trackId(index: number, item: IFormationCentreFormation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFormationCentreFormations(): void {
    this.eventSubscriber = this.eventManager.subscribe('formationCentreFormationListModification', () => this.loadPage());
  }

  delete(formationCentreFormation: IFormationCentreFormation): void {
    const modalRef = this.modalService.open(FormationCentreFormationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formationCentreFormation = formationCentreFormation;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IFormationCentreFormation[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/formation-centre-formation'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.formationCentreFormations = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  savemodale(): void {
    const savemodal = this.modalService.open(SaveFormationCentreComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodale(formationCentreFormation: IFormationCentreFormation): void {
    const updatemodal = this.modalService.open(UpdateFormationCentreComponent, { size: 'lg', backdrop: 'static' });
    updatemodal.componentInstance.formationCentreFormation = formationCentreFormation;
  }
}
