import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeAmenagement } from 'app/shared/model/type-amenagement.model';
import { TypeAmenagementService } from './type-amenagement.service';

@Component({
  templateUrl: './type-amenagement-delete-dialog.component.html',
})
export class TypeAmenagementDeleteDialogComponent {
  typeAmenagement?: ITypeAmenagement;

  constructor(
    protected typeAmenagementService: TypeAmenagementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeAmenagementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeAmenagementListModification');
      this.activeModal.close();
    });
  }
}
