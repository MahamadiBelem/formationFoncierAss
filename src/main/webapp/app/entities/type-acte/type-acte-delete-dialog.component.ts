import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeActe } from 'app/shared/model/type-acte.model';
import { TypeActeService } from './type-acte.service';

@Component({
  templateUrl: './type-acte-delete-dialog.component.html',
})
export class TypeActeDeleteDialogComponent {
  typeActe?: ITypeActe;

  constructor(protected typeActeService: TypeActeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeActeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeActeListModification');
      this.activeModal.close();
    });
  }
}
