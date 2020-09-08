import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeInfratructure } from 'app/shared/model/type-infratructure.model';

@Component({
  selector: 'jhi-type-infratructure-detail',
  templateUrl: './type-infratructure-detail.component.html',
})
export class TypeInfratructureDetailComponent implements OnInit {
  typeInfratructure: ITypeInfratructure | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeInfratructure }) => (this.typeInfratructure = typeInfratructure));
  }

  previousState(): void {
    window.history.back();
  }
}
