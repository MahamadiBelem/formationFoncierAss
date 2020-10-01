import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';
import { FormationCentreFormationService } from './formation-centre-formation.service';

@Component({
  templateUrl: './formation-centre-formation-delete-dialog.component.html',
})
export class FormationCentreFormationDeleteDialogComponent {
  formationCentreFormation?: IFormationCentreFormation;

  constructor(
    protected formationCentreFormationService: FormationCentreFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formationCentreFormationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formationCentreFormationListModification');
      this.activeModal.close();
    });
  }
}
