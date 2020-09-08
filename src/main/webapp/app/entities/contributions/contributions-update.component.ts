import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContributions, Contributions } from 'app/shared/model/contributions.model';
import { ContributionsService } from './contributions.service';

@Component({
  selector: 'jhi-contributions-update',
  templateUrl: './contributions-update.component.html',
})
export class ContributionsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleContribution: [null, [Validators.required]],
  });

  constructor(protected contributionsService: ContributionsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contributions }) => {
      this.updateForm(contributions);
    });
  }

  updateForm(contributions: IContributions): void {
    this.editForm.patchValue({
      id: contributions.id,
      libelleContribution: contributions.libelleContribution,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contributions = this.createFromForm();
    if (contributions.id !== undefined) {
      this.subscribeToSaveResponse(this.contributionsService.update(contributions));
    } else {
      this.subscribeToSaveResponse(this.contributionsService.create(contributions));
    }
  }

  private createFromForm(): IContributions {
    return {
      ...new Contributions(),
      id: this.editForm.get(['id'])!.value,
      libelleContribution: this.editForm.get(['libelleContribution'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContributions>>): void {
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
