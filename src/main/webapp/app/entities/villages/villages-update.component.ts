import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVillages, Villages } from 'app/shared/model/villages.model';
import { VillagesService } from './villages.service';

@Component({
  selector: 'jhi-villages-update',
  templateUrl: './villages-update.component.html',
})
export class VillagesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleVillage: [null, [Validators.required]],
  });

  constructor(protected villagesService: VillagesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ villages }) => {
      this.updateForm(villages);
    });
  }

  updateForm(villages: IVillages): void {
    this.editForm.patchValue({
      id: villages.id,
      libelleVillage: villages.libelleVillage,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const villages = this.createFromForm();
    if (villages.id !== undefined) {
      this.subscribeToSaveResponse(this.villagesService.update(villages));
    } else {
      this.subscribeToSaveResponse(this.villagesService.create(villages));
    }
  }

  private createFromForm(): IVillages {
    return {
      ...new Villages(),
      id: this.editForm.get(['id'])!.value,
      libelleVillage: this.editForm.get(['libelleVillage'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVillages>>): void {
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
