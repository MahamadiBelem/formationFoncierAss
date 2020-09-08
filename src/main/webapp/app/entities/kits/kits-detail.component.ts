import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKits } from 'app/shared/model/kits.model';

@Component({
  selector: 'jhi-kits-detail',
  templateUrl: './kits-detail.component.html',
})
export class KitsDetailComponent implements OnInit {
  kits: IKits | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kits }) => (this.kits = kits));
  }

  previousState(): void {
    window.history.back();
  }
}
