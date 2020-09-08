import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeInfratructure, TypeInfratructure } from 'app/shared/model/type-infratructure.model';
import { TypeInfratructureService } from './type-infratructure.service';

@Component({
  selector: 'jhi-type-infratructure-update',
  templateUrl: './type-infratructure-update.component.html',
})
export class TypeInfratructureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeInfracture: [],
  });

  constructor(
    protected typeInfratructureService: TypeInfratructureService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeInfratructure }) => {
      this.updateForm(typeInfratructure);
    });
  }

  updateForm(typeInfratructure: ITypeInfratructure): void {
    this.editForm.patchValue({
      id: typeInfratructure.id,
      libelleTypeInfracture: typeInfratructure.libelleTypeInfracture,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeInfratructure = this.createFromForm();
    if (typeInfratructure.id !== undefined) {
      this.subscribeToSaveResponse(this.typeInfratructureService.update(typeInfratructure));
    } else {
      this.subscribeToSaveResponse(this.typeInfratructureService.create(typeInfratructure));
    }
  }

  private createFromForm(): ITypeInfratructure {
    return {
      ...new TypeInfratructure(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeInfracture: this.editForm.get(['libelleTypeInfracture'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeInfratructure>>): void {
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
