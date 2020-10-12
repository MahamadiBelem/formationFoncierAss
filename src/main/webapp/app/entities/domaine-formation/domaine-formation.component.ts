import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDomaineFormation } from 'app/shared/model/domaine-formation.model';
import { DomaineFormationService } from './domaine-formation.service';
import { DomaineFormationDeleteDialogComponent } from './domaine-formation-delete-dialog.component';
import { SaveDomaineFormationComponent } from './save-domaine-formation/save-domaine-formation.component';
import { UpdateDomaineFormationComponent } from './update-domaine-formation/update-domaine-formation.component';

@Component({
  selector: 'jhi-domaine-formation',
  templateUrl: './domaine-formation.component.html',
})
export class DomaineFormationComponent implements OnInit, OnDestroy {
  domaineFormations?: IDomaineFormation[];
  eventSubscriber?: Subscription;

  constructor(
    protected domaineFormationService: DomaineFormationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.domaineFormationService.query().subscribe((res: HttpResponse<IDomaineFormation[]>) => (this.domaineFormations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDomaineFormations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDomaineFormation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDomaineFormations(): void {
    this.eventSubscriber = this.eventManager.subscribe('domaineFormationListModification', () => this.loadAll());
  }

  delete(domaineFormation: IDomaineFormation): void {
    const modalRef = this.modalService.open(DomaineFormationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.domaineFormation = domaineFormation;
  }

  savemodal(): void {
    const savemodale = this.modalService.open(SaveDomaineFormationComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodal(domaineFormation: IDomaineFormation): void {
    const updatemodale = this.modalService.open(UpdateDomaineFormationComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.domaineFormation = domaineFormation;
  }
}
