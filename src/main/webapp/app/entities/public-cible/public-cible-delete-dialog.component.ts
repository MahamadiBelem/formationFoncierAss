import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPublicCible } from 'app/shared/model/public-cible.model';
import { PublicCibleService } from './public-cible.service';

@Component({
  templateUrl: './public-cible-delete-dialog.component.html',
})
export class PublicCibleDeleteDialogComponent {
  publicCible?: IPublicCible;

  constructor(
    protected publicCibleService: PublicCibleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.publicCibleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('publicCibleListModification');
      this.activeModal.close();
    });
  }
}
