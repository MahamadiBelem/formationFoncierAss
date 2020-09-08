import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConditionAccess } from 'app/shared/model/condition-access.model';

@Component({
  selector: 'jhi-condition-access-detail',
  templateUrl: './condition-access-detail.component.html',
})
export class ConditionAccessDetailComponent implements OnInit {
  conditionAccess: IConditionAccess | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conditionAccess }) => (this.conditionAccess = conditionAccess));
  }

  previousState(): void {
    window.history.back();
  }
}
