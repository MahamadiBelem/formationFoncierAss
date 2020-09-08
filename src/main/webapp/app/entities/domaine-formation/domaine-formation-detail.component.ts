import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDomaineFormation } from 'app/shared/model/domaine-formation.model';

@Component({
  selector: 'jhi-domaine-formation-detail',
  templateUrl: './domaine-formation-detail.component.html',
})
export class DomaineFormationDetailComponent implements OnInit {
  domaineFormation: IDomaineFormation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ domaineFormation }) => (this.domaineFormation = domaineFormation));
  }

  previousState(): void {
    window.history.back();
  }
}
