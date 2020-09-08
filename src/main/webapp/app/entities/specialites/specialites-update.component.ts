import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISpecialites, Specialites } from 'app/shared/model/specialites.model';
import { SpecialitesService } from './specialites.service';

@Component({
  selector: 'jhi-specialites-update',
  templateUrl: './specialites-update.component.html',
})
export class SpecialitesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleSpecialite: [null, [Validators.required]],
  });

  constructor(protected specialitesService: SpecialitesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specialites }) => {
      this.updateForm(specialites);
    });
  }

  updateForm(specialites: ISpecialites): void {
    this.editForm.patchValue({
      id: specialites.id,
      libelleSpecialite: specialites.libelleSpecialite,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specialites = this.createFromForm();
    if (specialites.id !== undefined) {
      this.subscribeToSaveResponse(this.specialitesService.update(specialites));
    } else {
      this.subscribeToSaveResponse(this.specialitesService.create(specialites));
    }
  }

  private createFromForm(): ISpecialites {
    return {
      ...new Specialites(),
      id: this.editForm.get(['id'])!.value,
      libelleSpecialite: this.editForm.get(['libelleSpecialite'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecialites>>): void {
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
