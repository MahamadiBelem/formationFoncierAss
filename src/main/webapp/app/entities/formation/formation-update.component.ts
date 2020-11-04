import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormation, Formation } from 'app/shared/model/formation.model';
import { FormationService } from './formation.service';
import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from 'app/entities/sfr/sfr.service';

@Component({
  selector: 'jhi-formation-update',
  templateUrl: './formation-update.component.html',
})
export class FormationUpdateComponent implements OnInit {
  isSaving = false;
  sfrs: ISfr[] = [];

  editForm = this.fb.group({
    id: [],
    annee: [],
    nbrAgentForme: [],
    themeFormation: [],
    dureeTotFormation: [],
    sfrFormation: [],
  });

  constructor(
    protected formationService: FormationService,
    protected sfrService: SfrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formation }) => {
      this.updateForm(formation);

      this.sfrService.query().subscribe((res: HttpResponse<ISfr[]>) => (this.sfrs = res.body || []));
    });
  }

  updateForm(formation: IFormation): void {
    this.editForm.patchValue({
      id: formation.id,
      annee: formation.annee,
      nbrAgentForme: formation.nbrAgentForme,
      themeFormation: formation.themeFormation,
      dureeTotFormation: formation.dureeTotFormation,
      sfrFormation: formation.sfrFormation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formation = this.createFromForm();
    if (formation.id !== undefined) {
      this.subscribeToSaveResponse(this.formationService.update(formation));
    } else {
      this.subscribeToSaveResponse(this.formationService.create(formation));
    }
  }

  private createFromForm(): IFormation {
    return {
      ...new Formation(),
      id: this.editForm.get(['id'])!.value,
      annee: this.editForm.get(['annee'])!.value,
      nbrAgentForme: this.editForm.get(['nbrAgentForme'])!.value,
      themeFormation: this.editForm.get(['themeFormation'])!.value,
      dureeTotFormation: this.editForm.get(['dureeTotFormation'])!.value,
      sfrFormation: this.editForm.get(['sfrFormation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormation>>): void {
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
