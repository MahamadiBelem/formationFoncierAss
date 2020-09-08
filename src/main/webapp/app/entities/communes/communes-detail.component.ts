import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommunes } from 'app/shared/model/communes.model';

@Component({
  selector: 'jhi-communes-detail',
  templateUrl: './communes-detail.component.html',
})
export class CommunesDetailComponent implements OnInit {
  communes: ICommunes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ communes }) => (this.communes = communes));
  }

  previousState(): void {
    window.history.back();
  }
}
