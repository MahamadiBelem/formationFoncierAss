import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConditionAccess } from 'app/shared/model/condition-access.model';
import { ConditionAccessService } from './condition-access.service';

@Component({
  templateUrl: './condition-access-delete-dialog.component.html',
})
export class ConditionAccessDeleteDialogComponent {
  conditionAccess?: IConditionAccess;

  constructor(
    protected conditionAccessService: ConditionAccessService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.conditionAccessService.delete(id).subscribe(() => {
      this.eventManager.broadcast('conditionAccessListModification');
      this.activeModal.close();
    });
  }
}
