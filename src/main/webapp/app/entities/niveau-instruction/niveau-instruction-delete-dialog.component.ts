import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { NiveauInstructionService } from './niveau-instruction.service';

@Component({
  templateUrl: './niveau-instruction-delete-dialog.component.html',
})
export class NiveauInstructionDeleteDialogComponent {
  niveauInstruction?: INiveauInstruction;

  constructor(
    protected niveauInstructionService: NiveauInstructionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.niveauInstructionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('niveauInstructionListModification');
      this.activeModal.close();
    });
  }
}
