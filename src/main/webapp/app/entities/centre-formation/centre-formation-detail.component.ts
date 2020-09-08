import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICentreFormation } from 'app/shared/model/centre-formation.model';

@Component({
  selector: 'jhi-centre-formation-detail',
  templateUrl: './centre-formation-detail.component.html',
})
export class CentreFormationDetailComponent implements OnInit {
  centreFormation: ICentreFormation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centreFormation }) => (this.centreFormation = centreFormation));
  }

  previousState(): void {
    window.history.back();
  }
}
