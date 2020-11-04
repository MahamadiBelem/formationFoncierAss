import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImmaDomaine } from 'app/shared/model/imma-domaine.model';

@Component({
  selector: 'jhi-imma-domaine-detail',
  templateUrl: './imma-domaine-detail.component.html',
})
export class ImmaDomaineDetailComponent implements OnInit {
  immaDomaine: IImmaDomaine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ immaDomaine }) => (this.immaDomaine = immaDomaine));
  }

  previousState(): void {
    window.history.back();
  }
}
