import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecialites } from 'app/shared/model/specialites.model';

@Component({
  selector: 'jhi-specialites-detail',
  templateUrl: './specialites-detail.component.html',
})
export class SpecialitesDetailComponent implements OnInit {
  specialites: ISpecialites | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specialites }) => (this.specialites = specialites));
  }

  previousState(): void {
    window.history.back();
  }
}
