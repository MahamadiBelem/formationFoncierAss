import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INiveauRecrutement, NiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { NiveauRecrutementService } from './niveau-recrutement.service';

@Component({
  selector: 'jhi-niveau-recrutement-update',
  templateUrl: './niveau-recrutement-update.component.html',
})
export class NiveauRecrutementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleNiveau: [null, [Validators.required]],
  });

  constructor(
    protected niveauRecrutementService: NiveauRecrutementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveauRecrutement }) => {
      this.updateForm(niveauRecrutement);
    });
  }

  updateForm(niveauRecrutement: INiveauRecrutement): void {
    this.editForm.patchValue({
      id: niveauRecrutement.id,
      libelleNiveau: niveauRecrutement.libelleNiveau,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const niveauRecrutement = this.createFromForm();
    if (niveauRecrutement.id !== undefined) {
      this.subscribeToSaveResponse(this.niveauRecrutementService.update(niveauRecrutement));
    } else {
      this.subscribeToSaveResponse(this.niveauRecrutementService.create(niveauRecrutement));
    }
  }

  private createFromForm(): INiveauRecrutement {
    return {
      ...new NiveauRecrutement(),
      id: this.editForm.get(['id'])!.value,
      libelleNiveau: this.editForm.get(['libelleNiveau'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INiveauRecrutement>>): void {
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
