import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormationSFR } from 'app/shared/model/formation-sfr.model';
import { FormationSFRService } from './formation-sfr.service';

@Component({
  templateUrl: './formation-sfr-delete-dialog.component.html',
})
export class FormationSFRDeleteDialogComponent {
  formationSFR?: IFormationSFR;

  constructor(
    protected formationSFRService: FormationSFRService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formationSFRService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formationSFRListModification');
      this.activeModal.close();
    });
  }
}
