import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDomaineFormation, DomaineFormation } from 'app/shared/model/domaine-formation.model';
import { DomaineFormationService } from './domaine-formation.service';

@Component({
  selector: 'jhi-domaine-formation-update',
  templateUrl: './domaine-formation-update.component.html',
})
export class DomaineFormationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleDomaine: [null, [Validators.required]],
  });

  constructor(
    protected domaineFormationService: DomaineFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ domaineFormation }) => {
      this.updateForm(domaineFormation);
    });
  }

  updateForm(domaineFormation: IDomaineFormation): void {
    this.editForm.patchValue({
      id: domaineFormation.id,
      libelleDomaine: domaineFormation.libelleDomaine,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const domaineFormation = this.createFromForm();
    if (domaineFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.domaineFormationService.update(domaineFormation));
    } else {
      this.subscribeToSaveResponse(this.domaineFormationService.create(domaineFormation));
    }
  }

  private createFromForm(): IDomaineFormation {
    return {
      ...new DomaineFormation(),
      id: this.editForm.get(['id'])!.value,
      libelleDomaine: this.editForm.get(['libelleDomaine'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDomaineFormation>>): void {
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
}
