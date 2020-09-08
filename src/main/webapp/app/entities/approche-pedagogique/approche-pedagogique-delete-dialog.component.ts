import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { ApprochePedagogiqueService } from './approche-pedagogique.service';

@Component({
  templateUrl: './approche-pedagogique-delete-dialog.component.html',
})
export class ApprochePedagogiqueDeleteDialogComponent {
  approchePedagogique?: IApprochePedagogique;

  constructor(
    protected approchePedagogiqueService: ApprochePedagogiqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.approchePedagogiqueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('approchePedagogiqueListModification');
      this.activeModal.close();
    });
  }
}
