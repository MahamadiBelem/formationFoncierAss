import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGestionnaire, Gestionnaire } from 'app/shared/model/gestionnaire.model';
import { GestionnaireService } from './gestionnaire.service';

@Component({
  selector: 'jhi-gestionnaire-update',
  templateUrl: './gestionnaire-update.component.html',
})
export class GestionnaireUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nomComplet: [null, [Validators.required]],
    contactsGestionnaires: [],
    niveauEtude: [null, [Validators.required]],
  });

  constructor(protected gestionnaireService: GestionnaireService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gestionnaire }) => {
      this.updateForm(gestionnaire);
    });
  }

  updateForm(gestionnaire: IGestionnaire): void {
    this.editForm.patchValue({
      id: gestionnaire.id,
      nomComplet: gestionnaire.nomComplet,
      contactsGestionnaires: gestionnaire.contactsGestionnaires,
      niveauEtude: gestionnaire.niveauEtude,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gestionnaire = this.createFromForm();
    if (gestionnaire.id !== undefined) {
      this.subscribeToSaveResponse(this.gestionnaireService.update(gestionnaire));
    } else {
      this.subscribeToSaveResponse(this.gestionnaireService.create(gestionnaire));
    }
  }

  private createFromForm(): IGestionnaire {
    return {
      ...new Gestionnaire(),
      id: this.editForm.get(['id'])!.value,
      nomComplet: this.editForm.get(['nomComplet'])!.value,
      contactsGestionnaires: this.editForm.get(['contactsGestionnaires'])!.value,
      niveauEtude: this.editForm.get(['niveauEtude'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGestionnaire>>): void {
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
