import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';
import { FormationCentreFormationService } from './formation-centre-formation.service';
import { FormationCentreFormationDeleteDialogComponent } from './formation-centre-formation-delete-dialog.component';

@Component({
  selector: 'jhi-formation-centre-formation',
  templateUrl: './formation-centre-formation.component.html',
})
export class FormationCentreFormationComponent implements OnInit, OnDestroy {
  formationCentreFormations?: IFormationCentreFormation[];
  eventSubscriber?: Subscription;

  constructor(
    protected formationCentreFormationService: FormationCentreFormationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.formationCentreFormationService
      .query()
      .subscribe((res: HttpResponse<IFormationCentreFormation[]>) => (this.formationCentreFormations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFormationCentreFormations();
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
    this.eventSubscriber = this.eventManager.subscribe('formationCentreFormationListModification', () => this.loadAll());
  }

  delete(formationCentreFormation: IFormationCentreFormation): void {
    const modalRef = this.modalService.open(FormationCentreFormationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formationCentreFormation = formationCentreFormation;
  }
}
