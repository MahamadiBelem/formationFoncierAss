import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';

@Component({
  selector: 'jhi-formation-centre-formation-detail',
  templateUrl: './formation-centre-formation-detail.component.html',
})
export class FormationCentreFormationDetailComponent implements OnInit {
  formationCentreFormation: IFormationCentreFormation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formationCentreFormation }) => (this.formationCentreFormation = formationCentreFormation));
  }

  previousState(): void {
    window.history.back();
  }
}
