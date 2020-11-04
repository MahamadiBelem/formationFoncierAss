import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeDemandeur } from 'app/shared/model/type-demandeur.model';
import { TypeDemandeurService } from './type-demandeur.service';

@Component({
  templateUrl: './type-demandeur-delete-dialog.component.html',
})
export class TypeDemandeurDeleteDialogComponent {
  typeDemandeur?: ITypeDemandeur;

  constructor(
    protected typeDemandeurService: TypeDemandeurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeDemandeurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeDemandeurListModification');
      this.activeModal.close();
    });
  }
}
