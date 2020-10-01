import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormateurCentre } from 'app/shared/model/formateur-centre.model';
import { FormateurCentreService } from './formateur-centre.service';

@Component({
  templateUrl: './formateur-centre-delete-dialog.component.html',
})
export class FormateurCentreDeleteDialogComponent {
  formateurCentre?: IFormateurCentre;

  constructor(
    protected formateurCentreService: FormateurCentreService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formateurCentreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formateurCentreListModification');
      this.activeModal.close();
    });
  }
}
