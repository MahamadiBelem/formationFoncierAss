import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeDemandeur } from 'app/shared/model/type-demandeur.model';

@Component({
  selector: 'jhi-type-demandeur-detail',
  templateUrl: './type-demandeur-detail.component.html',
})
export class TypeDemandeurDetailComponent implements OnInit {
  typeDemandeur: ITypeDemandeur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeDemandeur }) => (this.typeDemandeur = typeDemandeur));
  }

  previousState(): void {
    window.history.back();
  }
}
