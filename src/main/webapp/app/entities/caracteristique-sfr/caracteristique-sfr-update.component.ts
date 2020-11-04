import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICaracteristiqueSfr, CaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';
import { CaracteristiqueSfrService } from './caracteristique-sfr.service';
import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from 'app/entities/sfr/sfr.service';

@Component({
  selector: 'jhi-caracteristique-sfr-update',
  templateUrl: './caracteristique-sfr-update.component.html',
})
export class CaracteristiqueSfrUpdateComponent implements OnInit {
  isSaving = false;
  sfrs: ISfr[] = [];

  editForm = this.fb.group({
    id: [],
    annee: [],
    nbrAgent: [],
    equipementInformatique: [],
    equipementCartographique: [],
    accesInternet: [],
    equipemementVehicule: [],
    caracteristiqueSfr: [],
  });

  constructor(
    protected caracteristiqueSfrService: CaracteristiqueSfrService,
    protected sfrService: SfrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caracteristiqueSfr }) => {
      this.updateForm(caracteristiqueSfr);

      this.sfrService.query().subscribe((res: HttpResponse<ISfr[]>) => (this.sfrs = res.body || []));
    });
  }

  updateForm(caracteristiqueSfr: ICaracteristiqueSfr): void {
    this.editForm.patchValue({
      id: caracteristiqueSfr.id,
      annee: caracteristiqueSfr.annee,
      nbrAgent: caracteristiqueSfr.nbrAgent,
      equipementInformatique: caracteristiqueSfr.equipementInformatique,
      equipementCartographique: caracteristiqueSfr.equipementCartographique,
      accesInternet: caracteristiqueSfr.accesInternet,
      equipemementVehicule: caracteristiqueSfr.equipemementVehicule,
      caracteristiqueSfr: caracteristiqueSfr.caracteristiqueSfr,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const caracteristiqueSfr = this.createFromForm();
    if (caracteristiqueSfr.id !== undefined) {
      this.subscribeToSaveResponse(this.caracteristiqueSfrService.update(caracteristiqueSfr));
    } else {
      this.subscribeToSaveResponse(this.caracteristiqueSfrService.create(caracteristiqueSfr));
    }
  }

  private createFromForm(): ICaracteristiqueSfr {
    return {
      ...new CaracteristiqueSfr(),
      id: this.editForm.get(['id'])!.value,
      annee: this.editForm.get(['annee'])!.value,
      nbrAgent: this.editForm.get(['nbrAgent'])!.value,
      equipementInformatique: this.editForm.get(['equipementInformatique'])!.value,
      equipementCartographique: this.editForm.get(['equipementCartographique'])!.value,
      accesInternet: this.editForm.get(['accesInternet'])!.value,
      equipemementVehicule: this.editForm.get(['equipemementVehicule'])!.value,
      caracteristiqueSfr: this.editForm.get(['caracteristiqueSfr'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaracteristiqueSfr>>): void {
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

  trackById(index: number, item: ISfr): any {
    return item.id;
  }
}
