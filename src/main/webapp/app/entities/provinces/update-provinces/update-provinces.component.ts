import { Component, OnInit, Input } from '@angular/core';
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
  selector: 'jhi-update-provinces',
  templateUrl: './update-provinces.component.html',
  styleUrls: ['./update-provinces.component.scss'],
})
export class UpdateProvincesComponent implements OnInit {
  @Input() public provinces: any;
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
    this.updateForm(this.provinces);

    this.activatedRoute.data.subscribe(({ provinces }) => {
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

  update(): void {
    this.isSaving = true;
    const provinces = this.createFromForm();
    if (provinces.id !== undefined) {
      this.subscribeToSaveResponse(this.provincesService.update(provinces));
    } else {
      this.subscribeToSaveResponse(this.provincesService.create(provinces));
    }

    this.cancel();
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
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRegion): any {
    return item.id;
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
}
