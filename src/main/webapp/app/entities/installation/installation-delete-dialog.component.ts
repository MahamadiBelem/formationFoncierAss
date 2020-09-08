import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstallation } from 'app/shared/model/installation.model';
import { InstallationService } from './installation.service';

@Component({
  templateUrl: './installation-delete-dialog.component.html',
})
export class InstallationDeleteDialogComponent {
  installation?: IInstallation;

  constructor(
    protected installationService: InstallationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.installationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('installationListModification');
      this.activeModal.close();
    });
  }
}
