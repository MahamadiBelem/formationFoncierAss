import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormationSFR } from 'app/shared/model/formation-sfr.model';

@Component({
  selector: 'jhi-formation-sfr-detail',
  templateUrl: './formation-sfr-detail.component.html',
})
export class FormationSFRDetailComponent implements OnInit {
  formationSFR: IFormationSFR | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formationSFR }) => (this.formationSFR = formationSFR));
  }

  previousState(): void {
    window.history.back();
  }
}
