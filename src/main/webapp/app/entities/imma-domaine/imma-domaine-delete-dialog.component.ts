import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImmaDomaine } from 'app/shared/model/imma-domaine.model';
import { ImmaDomaineService } from './imma-domaine.service';

@Component({
  templateUrl: './imma-domaine-delete-dialog.component.html',
})
export class ImmaDomaineDeleteDialogComponent {
  immaDomaine?: IImmaDomaine;

  constructor(
    protected immaDomaineService: ImmaDomaineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.immaDomaineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('immaDomaineListModification');
      this.activeModal.close();
    });
  }
}
