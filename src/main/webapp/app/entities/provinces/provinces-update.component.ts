import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProvinces, Provinces } from 'app/shared/model/provinces.model';
import { ProvincesService } from './provinces.service';

@Component({
  selector: 'jhi-provinces-update',
  templateUrl: './provinces-update.component.html',
})
export class ProvincesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleProvince: [null, [Validators.required]],
  });

  constructor(protected provincesService: ProvincesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ provinces }) => {
      this.updateForm(provinces);
    });
  }

  updateForm(provinces: IProvinces): void {
    this.editForm.patchValue({
      id: provinces.id,
      libelleProvince: provinces.libelleProvince,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const provinces = this.createFromForm();
    if (provinces.id !== undefined) {
      this.subscribeToSaveResponse(this.provincesService.update(provinces));
    } else {
      this.subscribeToSaveResponse(this.provincesService.create(provinces));
    }
  }

  private createFromForm(): IProvinces {
    return {
      ...new Provinces(),
      id: this.editForm.get(['id'])!.value,
      libelleProvince: this.editForm.get(['libelleProvince'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvinces>>): void {
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
