import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAmenagement, Amenagement } from 'app/shared/model/amenagement.model';
import { AmenagementService } from './amenagement.service';
import { ITypeAmenagement } from 'app/shared/model/type-amenagement.model';
import { TypeAmenagementService } from 'app/entities/type-amenagement/type-amenagement.service';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';

type SelectableEntity = ITypeAmenagement | ICentreFormation;

@Component({
  selector: 'jhi-amenagement-update',
  templateUrl: './amenagement-update.component.html',
})
export class AmenagementUpdateComponent implements OnInit {
  isSaving = false;
  typeamenagements: ITypeAmenagement[] = [];
  centreformations: ICentreFormation[] = [];

  editForm = this.fb.group({
    id: [],
    superficieTotal: [null, [Validators.required]],
    superficieEmbave: [null, [Validators.required]],
    etats: [null, [Validators.required]],
    typeamenagement: [],
    centreformation: [],
  });

  constructor(
    protected amenagementService: AmenagementService,
    protected typeAmenagementService: TypeAmenagementService,
    protected centreFormationService: CentreFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ amenagement }) => {
      this.updateForm(amenagement);

      this.typeAmenagementService.query().subscribe((res: HttpResponse<ITypeAmenagement[]>) => (this.typeamenagements = res.body || []));

      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));
    });
  }

  updateForm(amenagement: IAmenagement): void {
    this.editForm.patchValue({
      id: amenagement.id,
      superficieTotal: amenagement.superficieTotal,
      superficieEmbave: amenagement.superficieEmbave,
      etats: amenagement.etats,
      typeamenagement: amenagement.typeamenagement,
      centreformation: amenagement.centreformation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const amenagement = this.createFromForm();
    if (amenagement.id !== undefined) {
      this.subscribeToSaveResponse(this.amenagementService.update(amenagement));
    } else {
      this.subscribeToSaveResponse(this.amenagementService.create(amenagement));
    }
  }

  private createFromForm(): IAmenagement {
    return {
      ...new Amenagement(),
      id: this.editForm.get(['id'])!.value,
      superficieTotal: this.editForm.get(['superficieTotal'])!.value,
      superficieEmbave: this.editForm.get(['superficieEmbave'])!.value,
      etats: this.editForm.get(['etats'])!.value,
      typeamenagement: this.editForm.get(['typeamenagement'])!.value,
      centreformation: this.editForm.get(['centreformation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAmenagement>>): void {
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
