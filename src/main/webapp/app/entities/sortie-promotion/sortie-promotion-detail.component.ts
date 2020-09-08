import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';

@Component({
  selector: 'jhi-sortie-promotion-detail',
  templateUrl: './sortie-promotion-detail.component.html',
})
export class SortiePromotionDetailComponent implements OnInit {
  sortiePromotion: ISortiePromotion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sortiePromotion }) => (this.sortiePromotion = sortiePromotion));
  }

  previousState(): void {
    window.history.back();
  }
}
