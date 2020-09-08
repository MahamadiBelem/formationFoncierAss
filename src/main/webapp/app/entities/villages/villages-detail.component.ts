import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVillages } from 'app/shared/model/villages.model';

@Component({
  selector: 'jhi-villages-detail',
  templateUrl: './villages-detail.component.html',
})
export class VillagesDetailComponent implements OnInit {
  villages: IVillages | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ villages }) => (this.villages = villages));
  }

  previousState(): void {
    window.history.back();
  }
}
