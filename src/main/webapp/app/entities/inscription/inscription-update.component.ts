import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInscription, Inscription } from 'app/shared/model/inscription.model';
import { InscriptionService } from './inscription.service';
import { IFormations } from 'app/shared/model/formations.model';
import { FormationsService } from 'app/entities/formations/formations.service';
import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from 'app/entities/apprenantes/apprenantes.service';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';

type SelectableEntity = IFormations | IApprenantes | ICentreFormation;

@Component({
  selector: 'jhi-inscription-update',
  templateUrl: './inscription-update.component.html',
})
export class InscriptionUpdateComponent implements OnInit {
  isSaving = false;
  formations: IFormations[] = [];
  apprenantes: IApprenantes[] = [];
  centreformations: ICentreFormation[] = [];
  dateInscriptionDp: any;

  editForm = this.fb.group({
    id: [],
    anneesAcademique: [],
    dateInscription: [],
    inscription: [],
    inscriptionApprenant: [],
    inscriptionCentreFormation: [],
  });

  constructor(
    protected inscriptionService: InscriptionService,
    protected formationsService: FormationsService,
    protected apprenantesService: ApprenantesService,
    protected centreFormationService: CentreFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inscription }) => {
      this.updateForm(inscription);

      this.formationsService.query().subscribe((res: HttpResponse<IFormations[]>) => (this.formations = res.body || []));

      this.apprenantesService.query().subscribe((res: HttpResponse<IApprenantes[]>) => (this.apprenantes = res.body || []));

      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));
    });
  }

  updateForm(inscription: IInscription): void {
    this.editForm.patchValue({
      id: inscription.id,
      anneesAcademique: inscription.anneesAcademique,
      dateInscription: inscription.dateInscription,
      inscription: inscription.inscription,
      inscriptionApprenant: inscription.inscriptionApprenant,
      inscriptionCentreFormation: inscription.inscriptionCentreFormation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inscription = this.createFromForm();
    if (inscription.id !== undefined) {
      this.subscribeToSaveResponse(this.inscriptionService.update(inscription));
    } else {
      this.subscribeToSaveResponse(this.inscriptionService.create(inscription));
    }
  }

  private createFromForm(): IInscription {
    return {
      ...new Inscription(),
      id: this.editForm.get(['id'])!.value,
      anneesAcademique: this.editForm.get(['anneesAcademique'])!.value,
      dateInscription: this.editForm.get(['dateInscription'])!.value,
      inscription: this.editForm.get(['inscription'])!.value,
      inscriptionApprenant: this.editForm.get(['inscriptionApprenant'])!.value,
      inscriptionCentreFormation: this.editForm.get(['inscriptionCentreFormation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInscription>>): void {
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
