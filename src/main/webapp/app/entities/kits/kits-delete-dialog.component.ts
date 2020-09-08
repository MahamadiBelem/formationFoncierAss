import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKits } from 'app/shared/model/kits.model';
import { KitsService } from './kits.service';

@Component({
  templateUrl: './kits-delete-dialog.component.html',
})
export class KitsDeleteDialogComponent {
  kits?: IKits;

  constructor(protected kitsService: KitsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.kitsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('kitsListModification');
      this.activeModal.close();
    });
  }
}
