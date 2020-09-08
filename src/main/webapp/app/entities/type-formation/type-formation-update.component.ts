import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeFormation, TypeFormation } from 'app/shared/model/type-formation.model';
import { TypeFormationService } from './type-formation.service';

@Component({
  selector: 'jhi-type-formation-update',
  templateUrl: './type-formation-update.component.html',
})
export class TypeFormationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeFormation: [],
  });

  constructor(protected typeFormationService: TypeFormationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeFormation }) => {
      this.updateForm(typeFormation);
    });
  }

  updateForm(typeFormation: ITypeFormation): void {
    this.editForm.patchValue({
      id: typeFormation.id,
      libelleTypeFormation: typeFormation.libelleTypeFormation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeFormation = this.createFromForm();
    if (typeFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.typeFormationService.update(typeFormation));
    } else {
      this.subscribeToSaveResponse(this.typeFormationService.create(typeFormation));
    }
  }

  private createFromForm(): ITypeFormation {
    return {
      ...new TypeFormation(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeFormation: this.editForm.get(['libelleTypeFormation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeFormation>>): void {
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
