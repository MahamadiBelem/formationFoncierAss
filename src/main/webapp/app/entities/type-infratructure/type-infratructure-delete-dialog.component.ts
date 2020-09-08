import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeInfratructure } from 'app/shared/model/type-infratructure.model';
import { TypeInfratructureService } from './type-infratructure.service';

@Component({
  templateUrl: './type-infratructure-delete-dialog.component.html',
})
export class TypeInfratructureDeleteDialogComponent {
  typeInfratructure?: ITypeInfratructure;

  constructor(
    protected typeInfratructureService: TypeInfratructureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeInfratructureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeInfratructureListModification');
      this.activeModal.close();
    });
  }
}
