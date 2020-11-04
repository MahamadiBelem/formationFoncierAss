import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDossierApfr } from 'app/shared/model/dossier-apfr.model';

@Component({
  selector: 'jhi-dossier-apfr-detail',
  templateUrl: './dossier-apfr-detail.component.html',
})
export class DossierApfrDetailComponent implements OnInit {
  dossierApfr: IDossierApfr | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dossierApfr }) => (this.dossierApfr = dossierApfr));
  }

  previousState(): void {
    window.history.back();
  }
}
