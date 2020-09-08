import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommunes, Communes } from 'app/shared/model/communes.model';
import { CommunesService } from './communes.service';

@Component({
  selector: 'jhi-communes-update',
  templateUrl: './communes-update.component.html',
})
export class CommunesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleCommune: [null, [Validators.required]],
  });

  constructor(protected communesService: CommunesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ communes }) => {
      this.updateForm(communes);
    });
  }

  updateForm(communes: ICommunes): void {
    this.editForm.patchValue({
      id: communes.id,
      libelleCommune: communes.libelleCommune,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const communes = this.createFromForm();
    if (communes.id !== undefined) {
      this.subscribeToSaveResponse(this.communesService.update(communes));
    } else {
      this.subscribeToSaveResponse(this.communesService.create(communes));
    }
  }

  private createFromForm(): ICommunes {
    return {
      ...new Communes(),
      id: this.editForm.get(['id'])!.value,
      libelleCommune: this.editForm.get(['libelleCommune'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommunes>>): void {
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
