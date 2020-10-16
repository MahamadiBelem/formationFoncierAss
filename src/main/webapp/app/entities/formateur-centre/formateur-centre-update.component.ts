import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormateurCentre, FormateurCentre } from 'app/shared/model/formateur-centre.model';
import { FormateurCentreService } from './formateur-centre.service';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';
import { IFormateurs } from 'app/shared/model/formateurs.model';
import { FormateursService } from 'app/entities/formateurs/formateurs.service';
import { IRegime } from 'app/shared/model/regime.model';
import { RegimeService } from 'app/entities/regime/regime.service';

type SelectableEntity = ICentreFormation | IFormateurs | IRegime;

@Component({
  selector: 'jhi-formateur-centre-update',
  templateUrl: './formateur-centre-update.component.html',
})
export class FormateurCentreUpdateComponent implements OnInit {
  isSaving = false;
  centreformations: ICentreFormation[] = [];
  formateurs: IFormateurs[] = [];
  regimes: IRegime[] = [];
  dateEtablissementDp: any;

  editForm = this.fb.group({
    id: [],
    dateEtablissement: [],
    regimeFormateur: [null, [Validators.required]],
    formateurCentreFormation: [],
    formateurCentre: [],
    regimecentreformateur: [],
  });

  constructor(
    protected formateurCentreService: FormateurCentreService,
    protected centreFormationService: CentreFormationService,
    protected formateursService: FormateursService,
    protected regimeService: RegimeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formateurCentre }) => {
      this.updateForm(formateurCentre);

      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));

      this.formateursService.query().subscribe((res: HttpResponse<IFormateurs[]>) => (this.formateurs = res.body || []));

      this.regimeService.query().subscribe((res: HttpResponse<IRegime[]>) => (this.regimes = res.body || []));
    });
  }

  updateForm(formateurCentre: IFormateurCentre): void {
    this.editForm.patchValue({
      id: formateurCentre.id,
      dateEtablissement: formateurCentre.dateEtablissement,
      regimeFormateur: formateurCentre.regimeFormateur,
      formateurCentreFormation: formateurCentre.formateurCentreFormation,
      formateurCentre: formateurCentre.formateurCentre,
      regimecentreformateur: formateurCentre.regimecentreformateur,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formateurCentre = this.createFromForm();
    if (formateurCentre.id !== undefined) {
      this.subscribeToSaveResponse(this.formateurCentreService.update(formateurCentre));
    } else {
      this.subscribeToSaveResponse(this.formateurCentreService.create(formateurCentre));
    }
  }

  private createFromForm(): IFormateurCentre {
    return {
      ...new FormateurCentre(),
      id: this.editForm.get(['id'])!.value,
      dateEtablissement: this.editForm.get(['dateEtablissement'])!.value,
      regimeFormateur: this.editForm.get(['regimeFormateur'])!.value,
      formateurCentreFormation: this.editForm.get(['formateurCentreFormation'])!.value,
      formateurCentre: this.editForm.get(['formateurCentre'])!.value,
      regimecentreformateur: this.editForm.get(['regimecentreformateur'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormateurCentre>>): void {
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
