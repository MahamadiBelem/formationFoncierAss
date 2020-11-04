import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeActe } from 'app/shared/model/type-acte.model';

@Component({
  selector: 'jhi-type-acte-detail',
  templateUrl: './type-acte-detail.component.html',
})
export class TypeActeDetailComponent implements OnInit {
  typeActe: ITypeActe | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeActe }) => (this.typeActe = typeActe));
  }

  previousState(): void {
    window.history.back();
  }
}
