import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { NiveauRecrutementService } from './niveau-recrutement.service';

@Component({
  templateUrl: './niveau-recrutement-delete-dialog.component.html',
})
export class NiveauRecrutementDeleteDialogComponent {
  niveauRecrutement?: INiveauRecrutement;

  constructor(
    protected niveauRecrutementService: NiveauRecrutementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.niveauRecrutementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('niveauRecrutementListModification');
      this.activeModal.close();
    });
  }
}
