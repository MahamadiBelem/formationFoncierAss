import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVillages } from 'app/shared/model/villages.model';
import { VillagesService } from './villages.service';

@Component({
  templateUrl: './villages-delete-dialog.component.html',
})
export class VillagesDeleteDialogComponent {
  villages?: IVillages;

  constructor(protected villagesService: VillagesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.villagesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('villagesListModification');
      this.activeModal.close();
    });
  }
}
