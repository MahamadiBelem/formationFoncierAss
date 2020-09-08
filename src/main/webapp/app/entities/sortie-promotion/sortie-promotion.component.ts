import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from './sortie-promotion.service';
import { SortiePromotionDeleteDialogComponent } from './sortie-promotion-delete-dialog.component';

@Component({
  selector: 'jhi-sortie-promotion',
  templateUrl: './sortie-promotion.component.html',
})
export class SortiePromotionComponent implements OnInit, OnDestroy {
  sortiePromotions?: ISortiePromotion[];
  eventSubscriber?: Subscription;

  constructor(
    protected sortiePromotionService: SortiePromotionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.sortiePromotionService.query().subscribe((res: HttpResponse<ISortiePromotion[]>) => (this.sortiePromotions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSortiePromotions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISortiePromotion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSortiePromotions(): void {
    this.eventSubscriber = this.eventManager.subscribe('sortiePromotionListModification', () => this.loadAll());
  }

  delete(sortiePromotion: ISortiePromotion): void {
    const modalRef = this.modalService.open(SortiePromotionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sortiePromotion = sortiePromotion;
  }
}
