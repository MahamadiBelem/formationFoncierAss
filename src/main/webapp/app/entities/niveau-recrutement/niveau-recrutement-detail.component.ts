import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';

@Component({
  selector: 'jhi-niveau-recrutement-detail',
  templateUrl: './niveau-recrutement-detail.component.html',
})
export class NiveauRecrutementDetailComponent implements OnInit {
  niveauRecrutement: INiveauRecrutement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveauRecrutement }) => (this.niveauRecrutement = niveauRecrutement));
  }

  previousState(): void {
    window.history.back();
  }
}
