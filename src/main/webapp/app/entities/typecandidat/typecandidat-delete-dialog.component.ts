import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypecandidat } from 'app/shared/model/typecandidat.model';
import { TypecandidatService } from './typecandidat.service';

@Component({
  templateUrl: './typecandidat-delete-dialog.component.html',
})
export class TypecandidatDeleteDialogComponent {
  typecandidat?: ITypecandidat;

  constructor(
    protected typecandidatService: TypecandidatService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typecandidatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typecandidatListModification');
      this.activeModal.close();
    });
  }
}
