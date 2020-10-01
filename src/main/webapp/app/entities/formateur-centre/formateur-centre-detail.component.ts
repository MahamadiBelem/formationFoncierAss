import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormateurCentre } from 'app/shared/model/formateur-centre.model';

@Component({
  selector: 'jhi-formateur-centre-detail',
  templateUrl: './formateur-centre-detail.component.html',
})
export class FormateurCentreDetailComponent implements OnInit {
  formateurCentre: IFormateurCentre | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formateurCentre }) => (this.formateurCentre = formateurCentre));
  }

  previousState(): void {
    window.history.back();
  }
}
