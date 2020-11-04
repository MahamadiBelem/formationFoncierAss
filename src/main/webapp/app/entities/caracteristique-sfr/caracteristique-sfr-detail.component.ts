import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';

@Component({
  selector: 'jhi-caracteristique-sfr-detail',
  templateUrl: './caracteristique-sfr-detail.component.html',
})
export class CaracteristiqueSfrDetailComponent implements OnInit {
  caracteristiqueSfr: ICaracteristiqueSfr | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caracteristiqueSfr }) => (this.caracteristiqueSfr = caracteristiqueSfr));
  }

  previousState(): void {
    window.history.back();
  }
}
