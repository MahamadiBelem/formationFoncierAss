import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISourceFiancement, SourceFiancement } from 'app/shared/model/source-fiancement.model';
import { SourceFiancementService } from './source-fiancement.service';

@Component({
  selector: 'jhi-source-fiancement-update',
  templateUrl: './source-fiancement-update.component.html',
})
export class SourceFiancementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleSource: [null, [Validators.required]],
    partenaire: [],
  });

  constructor(
    protected sourceFiancementService: SourceFiancementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sourceFiancement }) => {
      this.updateForm(sourceFiancement);
    });
  }

  updateForm(sourceFiancement: ISourceFiancement): void {
    this.editForm.patchValue({
      id: sourceFiancement.id,
      libelleSource: sourceFiancement.libelleSource,
      partenaire: sourceFiancement.partenaire,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sourceFiancement = this.createFromForm();
    if (sourceFiancement.id !== undefined) {
      this.subscribeToSaveResponse(this.sourceFiancementService.update(sourceFiancement));
    } else {
      this.subscribeToSaveResponse(this.sourceFiancementService.create(sourceFiancement));
    }
  }

  private createFromForm(): ISourceFiancement {
    return {
      ...new SourceFiancement(),
      id: this.editForm.get(['id'])!.value,
      libelleSource: this.editForm.get(['libelleSource'])!.value,
      partenaire: this.editForm.get(['partenaire'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISourceFiancement>>): void {
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
