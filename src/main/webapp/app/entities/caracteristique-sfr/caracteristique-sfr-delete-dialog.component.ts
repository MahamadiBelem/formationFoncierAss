import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';
import { CaracteristiqueSfrService } from './caracteristique-sfr.service';

@Component({
  templateUrl: './caracteristique-sfr-delete-dialog.component.html',
})
export class CaracteristiqueSfrDeleteDialogComponent {
  caracteristiqueSfr?: ICaracteristiqueSfr;

  constructor(
    protected caracteristiqueSfrService: CaracteristiqueSfrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.caracteristiqueSfrService.delete(id).subscribe(() => {
      this.eventManager.broadcast('caracteristiqueSfrListModification');
      this.activeModal.close();
    });
  }
}
