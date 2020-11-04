import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from './sfr.service';

@Component({
  templateUrl: './sfr-delete-dialog.component.html',
})
export class SfrDeleteDialogComponent {
  sfr?: ISfr;

  constructor(protected sfrService: SfrService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sfrService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sfrListModification');
      this.activeModal.close();
    });
  }
}
