import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDossierApfr } from 'app/shared/model/dossier-apfr.model';
import { DossierApfrService } from './dossier-apfr.service';

@Component({
  templateUrl: './dossier-apfr-delete-dialog.component.html',
})
export class DossierApfrDeleteDialogComponent {
  dossierApfr?: IDossierApfr;

  constructor(
    protected dossierApfrService: DossierApfrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dossierApfrService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dossierApfrListModification');
      this.activeModal.close();
    });
  }
}
