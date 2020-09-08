import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeAmenagement } from 'app/shared/model/type-amenagement.model';

@Component({
  selector: 'jhi-type-amenagement-detail',
  templateUrl: './type-amenagement-detail.component.html',
})
export class TypeAmenagementDetailComponent implements OnInit {
  typeAmenagement: ITypeAmenagement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeAmenagement }) => (this.typeAmenagement = typeAmenagement));
  }

  previousState(): void {
    window.history.back();
  }
}
