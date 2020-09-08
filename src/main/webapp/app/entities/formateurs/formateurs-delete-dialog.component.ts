import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormateurs } from 'app/shared/model/formateurs.model';
import { FormateursService } from './formateurs.service';

@Component({
  templateUrl: './formateurs-delete-dialog.component.html',
})
export class FormateursDeleteDialogComponent {
  formateurs?: IFormateurs;

  constructor(
    protected formateursService: FormateursService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formateursService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formateursListModification');
      this.activeModal.close();
    });
  }
}
