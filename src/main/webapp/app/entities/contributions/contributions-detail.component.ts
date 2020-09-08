import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContributions } from 'app/shared/model/contributions.model';

@Component({
  selector: 'jhi-contributions-detail',
  templateUrl: './contributions-detail.component.html',
})
export class ContributionsDetailComponent implements OnInit {
  contributions: IContributions | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contributions }) => (this.contributions = contributions));
  }

  previousState(): void {
    window.history.back();
  }
}
