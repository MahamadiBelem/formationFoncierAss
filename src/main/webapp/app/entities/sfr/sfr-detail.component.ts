import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISfr } from 'app/shared/model/sfr.model';

@Component({
  selector: 'jhi-sfr-detail',
  templateUrl: './sfr-detail.component.html',
})
export class SfrDetailComponent implements OnInit {
  sfr: ISfr | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sfr }) => (this.sfr = sfr));
  }

  previousState(): void {
    window.history.back();
  }
}
