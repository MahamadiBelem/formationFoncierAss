import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAmenagement } from 'app/shared/model/amenagement.model';
import { AmenagementService } from './amenagement.service';

@Component({
  templateUrl: './amenagement-delete-dialog.component.html',
})
export class AmenagementDeleteDialogComponent {
  amenagement?: IAmenagement;

  constructor(
    protected amenagementService: AmenagementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.amenagementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('amenagementListModification');
      this.activeModal.close();
    });
  }
}
