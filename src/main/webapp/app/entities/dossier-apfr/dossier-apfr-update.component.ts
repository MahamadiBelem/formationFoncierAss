import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDossierApfr, DossierApfr } from 'app/shared/model/dossier-apfr.model';
import { DossierApfrService } from './dossier-apfr.service';
import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from 'app/entities/sfr/sfr.service';
import { ITypeDemandeur } from 'app/shared/model/type-demandeur.model';
import { TypeDemandeurService } from 'app/entities/type-demandeur/type-demandeur.service';
import { ITypeActe } from 'app/shared/model/type-acte.model';
import { TypeActeService } from 'app/entities/type-acte/type-acte.service';

type SelectableEntity = ISfr | ITypeDemandeur | ITypeActe;

@Component({
  selector: 'jhi-dossier-apfr-update',
  templateUrl: './dossier-apfr-update.component.html',
})
export class DossierApfrUpdateComponent implements OnInit {
  isSaving = false;
  sfrs: ISfr[] = [];
  typedemandeurs: ITypeDemandeur[] = [];
  typeactes: ITypeActe[] = [];
  dateReceptionCfvDp: any;
  dateRetourCtDp: any;
  dateEnregistrementDp: any;
  dateDepotStDp: any;
  dateRetourStDp: any;
  dateNotificationDp: any;
  dateSignatureDp: any;
  taxesTotaleDp: any;
  dateConstatationDp: any;

  editForm = this.fb.group({
    id: [],
    numApfr: [],
    trancheAge: [],
    dateReceptionCfv: [],
    dateRetourCt: [],
    dateEnregistrement: [],
    dateDepotSt: [],
    dateRetourSt: [],
    dateNotification: [],
    dateSignature: [],
    taxesTotale: [],
    donCommune: [],
    superficieSecurise: [],
    dateConstatation: [],
    sfrDossierApfr: [],
    dossierApfrTypeDemandeur: [],
    typeDossierApfr: [],
  });

  constructor(
    protected dossierApfrService: DossierApfrService,
    protected sfrService: SfrService,
    protected typeDemandeurService: TypeDemandeurService,
    protected typeActeService: TypeActeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dossierApfr }) => {
      this.updateForm(dossierApfr);

      this.sfrService.query().subscribe((res: HttpResponse<ISfr[]>) => (this.sfrs = res.body || []));

      this.typeDemandeurService.query().subscribe((res: HttpResponse<ITypeDemandeur[]>) => (this.typedemandeurs = res.body || []));

      this.typeActeService.query().subscribe((res: HttpResponse<ITypeActe[]>) => (this.typeactes = res.body || []));
    });
  }

  updateForm(dossierApfr: IDossierApfr): void {
    this.editForm.patchValue({
      id: dossierApfr.id,
      numApfr: dossierApfr.numApfr,
      trancheAge: dossierApfr.trancheAge,
      dateReceptionCfv: dossierApfr.dateReceptionCfv,
      dateRetourCt: dossierApfr.dateRetourCt,
      dateEnregistrement: dossierApfr.dateEnregistrement,
      dateDepotSt: dossierApfr.dateDepotSt,
      dateRetourSt: dossierApfr.dateRetourSt,
      dateNotification: dossierApfr.dateNotification,
      dateSignature: dossierApfr.dateSignature,
      taxesTotale: dossierApfr.taxesTotale,
      donCommune: dossierApfr.donCommune,
      superficieSecurise: dossierApfr.superficieSecurise,
      dateConstatation: dossierApfr.dateConstatation,
      sfrDossierApfr: dossierApfr.sfrDossierApfr,
      dossierApfrTypeDemandeur: dossierApfr.dossierApfrTypeDemandeur,
      typeDossierApfr: dossierApfr.typeDossierApfr,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dossierApfr = this.createFromForm();
    if (dossierApfr.id !== undefined) {
      this.subscribeToSaveResponse(this.dossierApfrService.update(dossierApfr));
    } else {
      this.subscribeToSaveResponse(this.dossierApfrService.create(dossierApfr));
    }
  }

  private createFromForm(): IDossierApfr {
    return {
      ...new DossierApfr(),
      id: this.editForm.get(['id'])!.value,
      numApfr: this.editForm.get(['numApfr'])!.value,
      trancheAge: this.editForm.get(['trancheAge'])!.value,
      dateReceptionCfv: this.editForm.get(['dateReceptionCfv'])!.value,
      dateRetourCt: this.editForm.get(['dateRetourCt'])!.value,
      dateEnregistrement: this.editForm.get(['dateEnregistrement'])!.value,
      dateDepotSt: this.editForm.get(['dateDepotSt'])!.value,
      dateRetourSt: this.editForm.get(['dateRetourSt'])!.value,
      dateNotification: this.editForm.get(['dateNotification'])!.value,
      dateSignature: this.editForm.get(['dateSignature'])!.value,
      taxesTotale: this.editForm.get(['taxesTotale'])!.value,
      donCommune: this.editForm.get(['donCommune'])!.value,
      superficieSecurise: this.editForm.get(['superficieSecurise'])!.value,
      dateConstatation: this.editForm.get(['dateConstatation'])!.value,
      sfrDossierApfr: this.editForm.get(['sfrDossierApfr'])!.value,
      dossierApfrTypeDemandeur: this.editForm.get(['dossierApfrTypeDemandeur'])!.value,
      typeDossierApfr: this.editForm.get(['typeDossierApfr'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDossierApfr>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
