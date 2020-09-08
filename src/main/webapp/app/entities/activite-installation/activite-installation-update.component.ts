import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IActiviteInstallation, ActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from './activite-installation.service';

@Component({
  selector: 'jhi-activite-installation-update',
  templateUrl: './activite-installation-update.component.html',
})
export class ActiviteInstallationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleActivite: [null, [Validators.required]],
  });

  constructor(
    protected activiteInstallationService: ActiviteInstallationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ activiteInstallation }) => {
      this.updateForm(activiteInstallation);
    });
  }

  updateForm(activiteInstallation: IActiviteInstallation): void {
    this.editForm.patchValue({
      id: activiteInstallation.id,
      libelleActivite: activiteInstallation.libelleActivite,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const activiteInstallation = this.createFromForm();
    if (activiteInstallation.id !== undefined) {
      this.subscribeToSaveResponse(this.activiteInstallationService.update(activiteInstallation));
    } else {
      this.subscribeToSaveResponse(this.activiteInstallationService.create(activiteInstallation));
    }
  }

  private createFromForm(): IActiviteInstallation {
    return {
      ...new ActiviteInstallation(),
      id: this.editForm.get(['id'])!.value,
      libelleActivite: this.editForm.get(['libelleActivite'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActiviteInstallation>>): void {
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
