import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAmenagement } from 'app/shared/model/amenagement.model';

@Component({
  selector: 'jhi-amenagement-detail',
  templateUrl: './amenagement-detail.component.html',
})
export class AmenagementDetailComponent implements OnInit {
  amenagement: IAmenagement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ amenagement }) => (this.amenagement = amenagement));
  }

  previousState(): void {
    window.history.back();
  }
}
