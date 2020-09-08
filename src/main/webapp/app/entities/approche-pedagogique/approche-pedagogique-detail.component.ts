import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';

@Component({
  selector: 'jhi-approche-pedagogique-detail',
  templateUrl: './approche-pedagogique-detail.component.html',
})
export class ApprochePedagogiqueDetailComponent implements OnInit {
  approchePedagogique: IApprochePedagogique | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ approchePedagogique }) => (this.approchePedagogique = approchePedagogique));
  }

  previousState(): void {
    window.history.back();
  }
}
