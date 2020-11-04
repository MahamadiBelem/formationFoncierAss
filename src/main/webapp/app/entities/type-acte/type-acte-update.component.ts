import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeActe, TypeActe } from 'app/shared/model/type-acte.model';
import { TypeActeService } from './type-acte.service';

@Component({
  selector: 'jhi-type-acte-update',
  templateUrl: './type-acte-update.component.html',
})
export class TypeActeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeActe: [],
  });

  constructor(protected typeActeService: TypeActeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeActe }) => {
      this.updateForm(typeActe);
    });
  }

  updateForm(typeActe: ITypeActe): void {
    this.editForm.patchValue({
      id: typeActe.id,
      libelleTypeActe: typeActe.libelleTypeActe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeActe = this.createFromForm();
    if (typeActe.id !== undefined) {
      this.subscribeToSaveResponse(this.typeActeService.update(typeActe));
    } else {
      this.subscribeToSaveResponse(this.typeActeService.create(typeActe));
    }
  }

  private createFromForm(): ITypeActe {
    return {
      ...new TypeActe(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeActe: this.editForm.get(['libelleTypeActe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeActe>>): void {
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
