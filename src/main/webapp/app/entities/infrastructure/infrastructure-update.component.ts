import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInfrastructure, Infrastructure } from 'app/shared/model/infrastructure.model';
import { InfrastructureService } from './infrastructure.service';
import { ITypeInfratructure } from 'app/shared/model/type-infratructure.model';
import { TypeInfratructureService } from 'app/entities/type-infratructure/type-infratructure.service';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';

type SelectableEntity = ITypeInfratructure | ICentreFormation;

@Component({
  selector: 'jhi-infrastructure-update',
  templateUrl: './infrastructure-update.component.html',
})
export class InfrastructureUpdateComponent implements OnInit {
  isSaving = false;
  typeinfratructures: ITypeInfratructure[] = [];
  centreformations: ICentreFormation[] = [];
  dateElaborationDp: any;

  editForm = this.fb.group({
    id: [],
    dateElaboration: [null, [Validators.required]],
    fonctionnalite: [null, [Validators.required]],
    etat: [null, [Validators.required]],
    typeinfrastructure: [],
    centreformation: [],
  });

  constructor(
    protected infrastructureService: InfrastructureService,
    protected typeInfratructureService: TypeInfratructureService,
    protected centreFormationService: CentreFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infrastructure }) => {
      this.updateForm(infrastructure);

      this.typeInfratructureService
        .query()
        .subscribe((res: HttpResponse<ITypeInfratructure[]>) => (this.typeinfratructures = res.body || []));

      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));
    });
  }

  updateForm(infrastructure: IInfrastructure): void {
    this.editForm.patchValue({
      id: infrastructure.id,
      dateElaboration: infrastructure.dateElaboration,
      fonctionnalite: infrastructure.fonctionnalite,
      etat: infrastructure.etat,
      typeinfrastructure: infrastructure.typeinfrastructure,
      centreformation: infrastructure.centreformation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const infrastructure = this.createFromForm();
    if (infrastructure.id !== undefined) {
      this.subscribeToSaveResponse(this.infrastructureService.update(infrastructure));
    } else {
      this.subscribeToSaveResponse(this.infrastructureService.create(infrastructure));
    }
  }

  private createFromForm(): IInfrastructure {
    return {
      ...new Infrastructure(),
      id: this.editForm.get(['id'])!.value,
      dateElaboration: this.editForm.get(['dateElaboration'])!.value,
      fonctionnalite: this.editForm.get(['fonctionnalite'])!.value,
      etat: this.editForm.get(['etat'])!.value,
      typeinfrastructure: this.editForm.get(['typeinfrastructure'])!.value,
      centreformation: this.editForm.get(['centreformation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInfrastructure>>): void {
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
