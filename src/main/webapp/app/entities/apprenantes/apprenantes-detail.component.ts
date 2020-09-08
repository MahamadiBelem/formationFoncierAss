import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApprenantes } from 'app/shared/model/apprenantes.model';

@Component({
  selector: 'jhi-apprenantes-detail',
  templateUrl: './apprenantes-detail.component.html',
})
export class ApprenantesDetailComponent implements OnInit {
  apprenantes: IApprenantes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apprenantes }) => (this.apprenantes = apprenantes));
  }

  previousState(): void {
    window.history.back();
  }
}
