import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContributions } from 'app/shared/model/contributions.model';
import { ContributionsService } from './contributions.service';

@Component({
  templateUrl: './contributions-delete-dialog.component.html',
})
export class ContributionsDeleteDialogComponent {
  contributions?: IContributions;

  constructor(
    protected contributionsService: ContributionsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contributionsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contributionsListModification');
      this.activeModal.close();
    });
  }
}
