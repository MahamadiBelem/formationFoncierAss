import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'jhi-centre-formation-detail',
  templateUrl: './centre-formation-detail.component.html',
})
export class CentreFormationDetailComponent implements OnInit {
  centreFormation: ICentreFormation | null = null;
  dateafficher: any;
  constructor(protected activatedRoute: ActivatedRoute, public datepipe: DatePipe) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centreFormation }) => (this.centreFormation = centreFormation));
    this.dateafficher = this.datepipe.transform(this.centreFormation?.dateOuverture, 'dd/MM,yyyy');
  }

  previousState(): void {
    window.history.back();
  }
}
