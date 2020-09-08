import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecialites } from 'app/shared/model/specialites.model';
import { SpecialitesService } from './specialites.service';

@Component({
  templateUrl: './specialites-delete-dialog.component.html',
})
export class SpecialitesDeleteDialogComponent {
  specialites?: ISpecialites;

  constructor(
    protected specialitesService: SpecialitesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.specialitesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('specialitesListModification');
      this.activeModal.close();
    });
  }
}
