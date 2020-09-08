import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeFormation } from 'app/shared/model/type-formation.model';

@Component({
  selector: 'jhi-type-formation-detail',
  templateUrl: './type-formation-detail.component.html',
})
export class TypeFormationDetailComponent implements OnInit {
  typeFormation: ITypeFormation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeFormation }) => (this.typeFormation = typeFormation));
  }

  previousState(): void {
    window.history.back();
  }
}
