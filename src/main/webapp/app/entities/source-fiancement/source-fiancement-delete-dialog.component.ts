import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISourceFiancement } from 'app/shared/model/source-fiancement.model';
import { SourceFiancementService } from './source-fiancement.service';

@Component({
  templateUrl: './source-fiancement-delete-dialog.component.html',
})
export class SourceFiancementDeleteDialogComponent {
  sourceFiancement?: ISourceFiancement;

  constructor(
    protected sourceFiancementService: SourceFiancementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sourceFiancementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sourceFiancementListModification');
      this.activeModal.close();
    });
  }
}
