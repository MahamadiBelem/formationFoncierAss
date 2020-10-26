import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISourceFiancement } from 'app/shared/model/source-fiancement.model';

@Component({
  selector: 'jhi-source-fiancement-detail',
  templateUrl: './source-fiancement-detail.component.html',
})
export class SourceFiancementDetailComponent implements OnInit {
  sourceFiancement: ISourceFiancement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sourceFiancement }) => (this.sourceFiancement = sourceFiancement));
  }

  previousState(): void {
    window.history.back();
  }
}
