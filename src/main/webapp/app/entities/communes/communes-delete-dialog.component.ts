import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommunes } from 'app/shared/model/communes.model';
import { CommunesService } from './communes.service';

@Component({
  templateUrl: './communes-delete-dialog.component.html',
})
export class CommunesDeleteDialogComponent {
  communes?: ICommunes;

  constructor(protected communesService: CommunesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.communesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('communesListModification');
      this.activeModal.close();
    });
  }
}
