import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormations } from 'app/shared/model/formations.model';
import { FormationsService } from './formations.service';

@Component({
  templateUrl: './formations-delete-dialog.component.html',
})
export class FormationsDeleteDialogComponent {
  formations?: IFormations;

  constructor(
    protected formationsService: FormationsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formationsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formationsListModification');
      this.activeModal.close();
    });
  }
}
