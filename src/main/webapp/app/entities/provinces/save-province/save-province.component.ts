import { Component, OnInit } from '@angular/core';
import { IRegion } from 'app/shared/model/region.model';
import { Validators, FormBuilder } from '@angular/forms';
import { ProvincesService } from '../provinces.service';
import { RegionService } from 'app/entities/region/region.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { IProvinces, Provinces } from 'app/shared/model/provinces.model';
import { Observable } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-province',
  templateUrl: './save-province.component.html',
  styleUrls: ['./save-province.component.scss'],
})
export class SaveProvinceComponent implements OnInit {
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
    private fb: FormBuilder,
    private activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
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
    this.subscribeToSaveResponse(this.provincesService.create(provinces));
    this.cancle();
    /*
    if (provinces.id !== undefined) {
      this.subscribeToSaveResponse(this.provincesService.update(provinces));
    } else {
     
    }
    */
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
    this.eventManager.broadcast('provincesListModification');
    //this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRegion): any {
    return item.id;
  }

  cancle(): void {
    this.activeModal.dismiss();
  }
}
