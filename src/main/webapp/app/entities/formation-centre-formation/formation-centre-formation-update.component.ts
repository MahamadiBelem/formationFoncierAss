import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormationCentreFormation, FormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';
import { FormationCentreFormationService } from './formation-centre-formation.service';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';
import { IFormations } from 'app/shared/model/formations.model';
import { FormationsService } from 'app/entities/formations/formations.service';

type SelectableEntity = ICentreFormation | IFormations;

@Component({
  selector: 'jhi-formation-centre-formation-update',
  templateUrl: './formation-centre-formation-update.component.html',
})
export class FormationCentreFormationUpdateComponent implements OnInit {
  isSaving = false;
  centreformations: ICentreFormation[] = [];
  formations: IFormations[] = [];
  datedebutDp: any;
  dateCloreDp: any;

  editForm = this.fb.group({
    id: [],
    datedebut: [],
    dateClore: [],
    formationcentre: [],
    centreformation: [],
  });

  constructor(
    protected formationCentreFormationService: FormationCentreFormationService,
    protected centreFormationService: CentreFormationService,
    protected formationsService: FormationsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));

    this.formationsService.query().subscribe((res: HttpResponse<IFormations[]>) => (this.formations = res.body || []));

    this.activatedRoute.data.subscribe(({ formationCentreFormation }) => {
      this.updateForm(formationCentreFormation);

      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));

      this.formationsService.query().subscribe((res: HttpResponse<IFormations[]>) => (this.formations = res.body || []));
    });
  }

  updateForm(formationCentreFormation: IFormationCentreFormation): void {
    this.editForm.patchValue({
      id: formationCentreFormation.id,
      datedebut: formationCentreFormation.datedebut,
      dateClore: formationCentreFormation.dateClore,
      formationcentre: formationCentreFormation.formationcentre,
      centreformation: formationCentreFormation.centreformation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formationCentreFormation = this.createFromForm();
    if (formationCentreFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.formationCentreFormationService.update(formationCentreFormation));
    } else {
      this.subscribeToSaveResponse(this.formationCentreFormationService.create(formationCentreFormation));
    }
  }

  private createFromForm(): IFormationCentreFormation {
    return {
      ...new FormationCentreFormation(),
      id: this.editForm.get(['id'])!.value,
      datedebut: this.editForm.get(['datedebut'])!.value,
      dateClore: this.editForm.get(['dateClore'])!.value,
      formationcentre: this.editForm.get(['formationcentre'])!.value,
      centreformation: this.editForm.get(['centreformation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormationCentreFormation>>): void {
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
