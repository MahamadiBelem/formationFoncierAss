import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPromoteurs } from 'app/shared/model/promoteurs.model';

@Component({
  selector: 'jhi-promoteurs-detail',
  templateUrl: './promoteurs-detail.component.html',
})
export class PromoteursDetailComponent implements OnInit {
  promoteurs: IPromoteurs | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promoteurs }) => (this.promoteurs = promoteurs));
  }

  previousState(): void {
    window.history.back();
  }
}
