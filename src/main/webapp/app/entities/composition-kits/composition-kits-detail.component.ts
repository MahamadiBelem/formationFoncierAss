import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompositionKits } from 'app/shared/model/composition-kits.model';

@Component({
  selector: 'jhi-composition-kits-detail',
  templateUrl: './composition-kits-detail.component.html',
})
export class CompositionKitsDetailComponent implements OnInit {
  compositionKits: ICompositionKits | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compositionKits }) => (this.compositionKits = compositionKits));
  }

  previousState(): void {
    window.history.back();
  }
}
