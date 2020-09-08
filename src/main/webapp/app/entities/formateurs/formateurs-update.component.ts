import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormateurs, Formateurs } from 'app/shared/model/formateurs.model';
import { FormateursService } from './formateurs.service';

@Component({
  selector: 'jhi-formateurs-update',
  templateUrl: './formateurs-update.component.html',
})
export class FormateursUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nomComplet: [null, [Validators.required]],
    specialite: [null, [Validators.required]],
    regime: [null, [Validators.required]],
  });

  constructor(protected formateursService: FormateursService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formateurs }) => {
      this.updateForm(formateurs);
    });
  }

  updateForm(formateurs: IFormateurs): void {
    this.editForm.patchValue({
      id: formateurs.id,
      nomComplet: formateurs.nomComplet,
      specialite: formateurs.specialite,
      regime: formateurs.regime,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formateurs = this.createFromForm();
    if (formateurs.id !== undefined) {
      this.subscribeToSaveResponse(this.formateursService.update(formateurs));
    } else {
      this.subscribeToSaveResponse(this.formateursService.create(formateurs));
    }
  }

  private createFromForm(): IFormateurs {
    return {
      ...new Formateurs(),
      id: this.editForm.get(['id'])!.value,
      nomComplet: this.editForm.get(['nomComplet'])!.value,
      specialite: this.editForm.get(['specialite'])!.value,
      regime: this.editForm.get(['regime'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormateurs>>): void {
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
