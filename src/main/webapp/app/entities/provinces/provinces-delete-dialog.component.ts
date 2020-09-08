import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvinces } from 'app/shared/model/provinces.model';
import { ProvincesService } from './provinces.service';

@Component({
  templateUrl: './provinces-delete-dialog.component.html',
})
export class ProvincesDeleteDialogComponent {
  provinces?: IProvinces;

  constructor(protected provincesService: ProvincesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.provincesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('provincesListModification');
      this.activeModal.close();
    });
  }
}
