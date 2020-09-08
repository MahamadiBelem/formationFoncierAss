import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompositionKits } from 'app/shared/model/composition-kits.model';
import { CompositionKitsService } from './composition-kits.service';

@Component({
  templateUrl: './composition-kits-delete-dialog.component.html',
})
export class CompositionKitsDeleteDialogComponent {
  compositionKits?: ICompositionKits;

  constructor(
    protected compositionKitsService: CompositionKitsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.compositionKitsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('compositionKitsListModification');
      this.activeModal.close();
    });
  }
}
