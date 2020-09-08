import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from './apprenantes.service';

@Component({
  templateUrl: './apprenantes-delete-dialog.component.html',
})
export class ApprenantesDeleteDialogComponent {
  apprenantes?: IApprenantes;

  constructor(
    protected apprenantesService: ApprenantesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.apprenantesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('apprenantesListModification');
      this.activeModal.close();
    });
  }
}
