import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from './sortie-promotion.service';

@Component({
  templateUrl: './sortie-promotion-delete-dialog.component.html',
})
export class SortiePromotionDeleteDialogComponent {
  sortiePromotion?: ISortiePromotion;

  constructor(
    protected sortiePromotionService: SortiePromotionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sortiePromotionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sortiePromotionListModification');
      this.activeModal.close();
    });
  }
}
