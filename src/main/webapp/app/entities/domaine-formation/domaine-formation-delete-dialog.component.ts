import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDomaineFormation } from 'app/shared/model/domaine-formation.model';
import { DomaineFormationService } from './domaine-formation.service';

@Component({
  templateUrl: './domaine-formation-delete-dialog.component.html',
})
export class DomaineFormationDeleteDialogComponent {
  domaineFormation?: IDomaineFormation;

  constructor(
    protected domaineFormationService: DomaineFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.domaineFormationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('domaineFormationListModification');
      this.activeModal.close();
    });
  }
}
