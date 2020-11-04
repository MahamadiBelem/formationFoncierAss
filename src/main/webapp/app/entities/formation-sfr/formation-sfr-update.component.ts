import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormationSFR, FormationSFR } from 'app/shared/model/formation-sfr.model';
import { FormationSFRService } from './formation-sfr.service';
import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from 'app/entities/sfr/sfr.service';

@Component({
  selector: 'jhi-formation-sfr-update',
  templateUrl: './formation-sfr-update.component.html',
})
export class FormationSFRUpdateComponent implements OnInit {
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
    protected formationSFRService: FormationSFRService,
    protected sfrService: SfrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formationSFR }) => {
      this.updateForm(formationSFR);

      this.sfrService.query().subscribe((res: HttpResponse<ISfr[]>) => (this.sfrs = res.body || []));
    });
  }

  updateForm(formationSFR: IFormationSFR): void {
    this.editForm.patchValue({
      id: formationSFR.id,
      annee: formationSFR.annee,
      nbrAgentForme: formationSFR.nbrAgentForme,
      themeFormation: formationSFR.themeFormation,
      dureeTotFormation: formationSFR.dureeTotFormation,
      sfrFormation: formationSFR.sfrFormation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formationSFR = this.createFromForm();
    if (formationSFR.id !== undefined) {
      this.subscribeToSaveResponse(this.formationSFRService.update(formationSFR));
    } else {
      this.subscribeToSaveResponse(this.formationSFRService.create(formationSFR));
    }
  }

  private createFromForm(): IFormationSFR {
    return {
      ...new FormationSFR(),
      id: this.editForm.get(['id'])!.value,
      annee: this.editForm.get(['annee'])!.value,
      nbrAgentForme: this.editForm.get(['nbrAgentForme'])!.value,
      themeFormation: this.editForm.get(['themeFormation'])!.value,
      dureeTotFormation: this.editForm.get(['dureeTotFormation'])!.value,
      sfrFormation: this.editForm.get(['sfrFormation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormationSFR>>): void {
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
