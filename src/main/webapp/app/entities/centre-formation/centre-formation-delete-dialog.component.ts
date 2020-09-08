import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from './centre-formation.service';

@Component({
  templateUrl: './centre-formation-delete-dialog.component.html',
})
export class CentreFormationDeleteDialogComponent {
  centreFormation?: ICentreFormation;

  constructor(
    protected centreFormationService: CentreFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.centreFormationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('centreFormationListModification');
      this.activeModal.close();
    });
  }
}
