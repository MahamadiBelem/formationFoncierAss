import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPromoteurs } from 'app/shared/model/promoteurs.model';
import { PromoteursService } from './promoteurs.service';

@Component({
  templateUrl: './promoteurs-delete-dialog.component.html',
})
export class PromoteursDeleteDialogComponent {
  promoteurs?: IPromoteurs;

  constructor(
    protected promoteursService: PromoteursService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.promoteursService.delete(id).subscribe(() => {
      this.eventManager.broadcast('promoteursListModification');
      this.activeModal.close();
    });
  }
}
