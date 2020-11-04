import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProfilPersonnel, ProfilPersonnel } from 'app/shared/model/profil-personnel.model';
import { ProfilPersonnelService } from './profil-personnel.service';

@Component({
  selector: 'jhi-profil-personnel-update',
  templateUrl: './profil-personnel-update.component.html',
})
export class ProfilPersonnelUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleProfil: [],
  });

  constructor(
    protected profilPersonnelService: ProfilPersonnelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ profilPersonnel }) => {
      this.updateForm(profilPersonnel);
    });
  }

  updateForm(profilPersonnel: IProfilPersonnel): void {
    this.editForm.patchValue({
      id: profilPersonnel.id,
      libelleProfil: profilPersonnel.libelleProfil,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const profilPersonnel = this.createFromForm();
    if (profilPersonnel.id !== undefined) {
      this.subscribeToSaveResponse(this.profilPersonnelService.update(profilPersonnel));
    } else {
      this.subscribeToSaveResponse(this.profilPersonnelService.create(profilPersonnel));
    }
  }

  private createFromForm(): IProfilPersonnel {
    return {
      ...new ProfilPersonnel(),
      id: this.editForm.get(['id'])!.value,
      libelleProfil: this.editForm.get(['libelleProfil'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfilPersonnel>>): void {
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
