import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from './activite-installation.service';

@Component({
  templateUrl: './activite-installation-delete-dialog.component.html',
})
export class ActiviteInstallationDeleteDialogComponent {
  activiteInstallation?: IActiviteInstallation;

  constructor(
    protected activiteInstallationService: ActiviteInstallationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.activiteInstallationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('activiteInstallationListModification');
      this.activeModal.close();
    });
  }
}
