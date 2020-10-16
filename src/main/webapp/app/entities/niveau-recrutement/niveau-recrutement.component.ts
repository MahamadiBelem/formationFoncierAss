import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { NiveauRecrutementService } from './niveau-recrutement.service';
import { NiveauRecrutementDeleteDialogComponent } from './niveau-recrutement-delete-dialog.component';
import { SaveNiveauComponent } from '../niveau-instruction/save-niveau/save-niveau.component';
import { SaveNiveauRecrutementComponent } from './save-niveau-recrutement/save-niveau-recrutement.component';
import { UpdateNiveauRecrutementComponent } from './update-niveau-recrutement/update-niveau-recrutement.component';

@Component({
  selector: 'jhi-niveau-recrutement',
  templateUrl: './niveau-recrutement.component.html',
})
export class NiveauRecrutementComponent implements OnInit, OnDestroy {
  niveauRecrutements?: INiveauRecrutement[];
  eventSubscriber?: Subscription;

  constructor(
    protected niveauRecrutementService: NiveauRecrutementService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.niveauRecrutementService
      .query()
      .subscribe((res: HttpResponse<INiveauRecrutement[]>) => (this.niveauRecrutements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNiveauRecrutements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INiveauRecrutement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNiveauRecrutements(): void {
    this.eventSubscriber = this.eventManager.subscribe('niveauRecrutementListModification', () => this.loadAll());
  }

  delete(niveauRecrutement: INiveauRecrutement): void {
    const modalRef = this.modalService.open(NiveauRecrutementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.niveauRecrutement = niveauRecrutement;
  }

  savemodal(): void {
    const savemodal = this.modalService.open(SaveNiveauRecrutementComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodal(niveauRecrutement: INiveauRecrutement): void {
    const updatemodal = this.modalService.open(UpdateNiveauRecrutementComponent, { size: 'lg', backdrop: 'static' });
    updatemodal.componentInstance.niveauRecrutement = niveauRecrutement;
  }
}
