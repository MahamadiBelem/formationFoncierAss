import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompositionKits, CompositionKits } from 'app/shared/model/composition-kits.model';
import { CompositionKitsService } from './composition-kits.service';

@Component({
  selector: 'jhi-composition-kits-update',
  templateUrl: './composition-kits-update.component.html',
})
export class CompositionKitsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleKits: [],
    quantiteKits: [],
  });

  constructor(
    protected compositionKitsService: CompositionKitsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compositionKits }) => {
      this.updateForm(compositionKits);
    });
  }

  updateForm(compositionKits: ICompositionKits): void {
    this.editForm.patchValue({
      id: compositionKits.id,
      libelleKits: compositionKits.libelleKits,
      quantiteKits: compositionKits.quantiteKits,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compositionKits = this.createFromForm();
    if (compositionKits.id !== undefined) {
      this.subscribeToSaveResponse(this.compositionKitsService.update(compositionKits));
    } else {
      this.subscribeToSaveResponse(this.compositionKitsService.create(compositionKits));
    }
  }

  private createFromForm(): ICompositionKits {
    return {
      ...new CompositionKits(),
      id: this.editForm.get(['id'])!.value,
      libelleKits: this.editForm.get(['libelleKits'])!.value,
      quantiteKits: this.editForm.get(['quantiteKits'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompositionKits>>): void {
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
