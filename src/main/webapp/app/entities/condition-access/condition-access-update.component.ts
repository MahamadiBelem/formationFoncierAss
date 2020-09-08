import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConditionAccess, ConditionAccess } from 'app/shared/model/condition-access.model';
import { ConditionAccessService } from './condition-access.service';

@Component({
  selector: 'jhi-condition-access-update',
  templateUrl: './condition-access-update.component.html',
})
export class ConditionAccessUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleConditon: [null, [Validators.required]],
  });

  constructor(
    protected conditionAccessService: ConditionAccessService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conditionAccess }) => {
      this.updateForm(conditionAccess);
    });
  }

  updateForm(conditionAccess: IConditionAccess): void {
    this.editForm.patchValue({
      id: conditionAccess.id,
      libelleConditon: conditionAccess.libelleConditon,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const conditionAccess = this.createFromForm();
    if (conditionAccess.id !== undefined) {
      this.subscribeToSaveResponse(this.conditionAccessService.update(conditionAccess));
    } else {
      this.subscribeToSaveResponse(this.conditionAccessService.create(conditionAccess));
    }
  }

  private createFromForm(): IConditionAccess {
    return {
      ...new ConditionAccess(),
      id: this.editForm.get(['id'])!.value,
      libelleConditon: this.editForm.get(['libelleConditon'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConditionAccess>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
