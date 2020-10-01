import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProvinces, Provinces } from 'app/shared/model/provinces.model';
import { ProvincesService } from './provinces.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region/region.service';

@Component({
  selector: 'jhi-provinces-update',
  templateUrl: './provinces-update.component.html',
})
export class ProvincesUpdateComponent implements OnInit {
  isSaving = false;
  regions: IRegion[] = [];

  editForm = this.fb.group({
    id: [],
    libelleProvince: [null, [Validators.required]],
    region: [],
  });

  constructor(
    protected provincesService: ProvincesService,
    protected regionService: RegionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ provinces }) => {
      this.updateForm(provinces);

      this.regionService.query().subscribe((res: HttpResponse<IRegion[]>) => (this.regions = res.body || []));
    });
  }

  updateForm(provinces: IProvinces): void {
    this.editForm.patchValue({
      id: provinces.id,
      libelleProvince: provinces.libelleProvince,
      region: provinces.region,
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
      region: this.editForm.get(['region'])!.value,
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

  trackById(index: number, item: IRegion): any {
    return item.id;
  }
}
