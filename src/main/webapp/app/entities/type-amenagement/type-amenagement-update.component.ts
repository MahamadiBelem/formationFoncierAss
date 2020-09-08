import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeAmenagement, TypeAmenagement } from 'app/shared/model/type-amenagement.model';
import { TypeAmenagementService } from './type-amenagement.service';

@Component({
  selector: 'jhi-type-amenagement-update',
  templateUrl: './type-amenagement-update.component.html',
})
export class TypeAmenagementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeAmenagement: [],
  });

  constructor(
    protected typeAmenagementService: TypeAmenagementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeAmenagement }) => {
      this.updateForm(typeAmenagement);
    });
  }

  updateForm(typeAmenagement: ITypeAmenagement): void {
    this.editForm.patchValue({
      id: typeAmenagement.id,
      libelleTypeAmenagement: typeAmenagement.libelleTypeAmenagement,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeAmenagement = this.createFromForm();
    if (typeAmenagement.id !== undefined) {
      this.subscribeToSaveResponse(this.typeAmenagementService.update(typeAmenagement));
    } else {
      this.subscribeToSaveResponse(this.typeAmenagementService.create(typeAmenagement));
    }
  }

  private createFromForm(): ITypeAmenagement {
    return {
      ...new TypeAmenagement(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeAmenagement: this.editForm.get(['libelleTypeAmenagement'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeAmenagement>>): void {
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
