import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormations, Formations } from 'app/shared/model/formations.model';
import { FormationsService } from './formations.service';
import { ITypeFormation } from 'app/shared/model/type-formation.model';
import { TypeFormationService } from 'app/entities/type-formation/type-formation.service';
import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from 'app/entities/apprenantes/apprenantes.service';

type SelectableEntity = ITypeFormation | IApprenantes;

@Component({
  selector: 'jhi-formations-update',
  templateUrl: './formations-update.component.html',
})
export class FormationsUpdateComponent implements OnInit {
  isSaving = false;
  typeformations: ITypeFormation[] = [];
  apprenantes: IApprenantes[] = [];

  editForm = this.fb.group({
    id: [],
    theme: [],
    lebelleFormation: [],
    coutFormation: [],
    sourceFinancement: [],
    typeamenagement: [],
    formations: [],
  });

  constructor(
    protected formationsService: FormationsService,
    protected typeFormationService: TypeFormationService,
    protected apprenantesService: ApprenantesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formations }) => {
      this.updateForm(formations);

      this.typeFormationService.query().subscribe((res: HttpResponse<ITypeFormation[]>) => (this.typeformations = res.body || []));

      this.apprenantesService.query().subscribe((res: HttpResponse<IApprenantes[]>) => (this.apprenantes = res.body || []));
    });
  }

  updateForm(formations: IFormations): void {
    this.editForm.patchValue({
      id: formations.id,
      theme: formations.theme,
      lebelleFormation: formations.lebelleFormation,
      coutFormation: formations.coutFormation,
      sourceFinancement: formations.sourceFinancement,
      typeamenagement: formations.typeamenagement,
      formations: formations.formations,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formations = this.createFromForm();
    if (formations.id !== undefined) {
      this.subscribeToSaveResponse(this.formationsService.update(formations));
    } else {
      this.subscribeToSaveResponse(this.formationsService.create(formations));
    }
  }

  private createFromForm(): IFormations {
    return {
      ...new Formations(),
      id: this.editForm.get(['id'])!.value,
      theme: this.editForm.get(['theme'])!.value,
      lebelleFormation: this.editForm.get(['lebelleFormation'])!.value,
      coutFormation: this.editForm.get(['coutFormation'])!.value,
      sourceFinancement: this.editForm.get(['sourceFinancement'])!.value,
      typeamenagement: this.editForm.get(['typeamenagement'])!.value,
      formations: this.editForm.get(['formations'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormations>>): void {
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

  getSelected(selectedVals: IApprenantes[], option: IApprenantes): IApprenantes {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
