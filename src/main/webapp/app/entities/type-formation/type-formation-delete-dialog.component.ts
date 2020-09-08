import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeFormation } from 'app/shared/model/type-formation.model';
import { TypeFormationService } from './type-formation.service';

@Component({
  templateUrl: './type-formation-delete-dialog.component.html',
})
export class TypeFormationDeleteDialogComponent {
  typeFormation?: ITypeFormation;

  constructor(
    protected typeFormationService: TypeFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeFormationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeFormationListModification');
      this.activeModal.close();
    });
  }
}
