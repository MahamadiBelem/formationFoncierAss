import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeDemandeur, TypeDemandeur } from 'app/shared/model/type-demandeur.model';
import { TypeDemandeurService } from './type-demandeur.service';

@Component({
  selector: 'jhi-type-demandeur-update',
  templateUrl: './type-demandeur-update.component.html',
})
export class TypeDemandeurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeDemandeur: [],
  });

  constructor(protected typeDemandeurService: TypeDemandeurService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeDemandeur }) => {
      this.updateForm(typeDemandeur);
    });
  }

  updateForm(typeDemandeur: ITypeDemandeur): void {
    this.editForm.patchValue({
      id: typeDemandeur.id,
      libelleTypeDemandeur: typeDemandeur.libelleTypeDemandeur,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeDemandeur = this.createFromForm();
    if (typeDemandeur.id !== undefined) {
      this.subscribeToSaveResponse(this.typeDemandeurService.update(typeDemandeur));
    } else {
      this.subscribeToSaveResponse(this.typeDemandeurService.create(typeDemandeur));
    }
  }

  private createFromForm(): ITypeDemandeur {
    return {
      ...new TypeDemandeur(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeDemandeur: this.editForm.get(['libelleTypeDemandeur'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeDemandeur>>): void {
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
