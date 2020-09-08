import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPublicCible } from 'app/shared/model/public-cible.model';

@Component({
  selector: 'jhi-public-cible-detail',
  templateUrl: './public-cible-detail.component.html',
})
export class PublicCibleDetailComponent implements OnInit {
  publicCible: IPublicCible | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ publicCible }) => (this.publicCible = publicCible));
  }

  previousState(): void {
    window.history.back();
  }
}
