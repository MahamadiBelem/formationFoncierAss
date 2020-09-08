import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IApprochePedagogique, ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { ApprochePedagogiqueService } from './approche-pedagogique.service';

@Component({
  selector: 'jhi-approche-pedagogique-update',
  templateUrl: './approche-pedagogique-update.component.html',
})
export class ApprochePedagogiqueUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleApproche: [null, [Validators.required]],
  });

  constructor(
    protected approchePedagogiqueService: ApprochePedagogiqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ approchePedagogique }) => {
      this.updateForm(approchePedagogique);
    });
  }

  updateForm(approchePedagogique: IApprochePedagogique): void {
    this.editForm.patchValue({
      id: approchePedagogique.id,
      libelleApproche: approchePedagogique.libelleApproche,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const approchePedagogique = this.createFromForm();
    if (approchePedagogique.id !== undefined) {
      this.subscribeToSaveResponse(this.approchePedagogiqueService.update(approchePedagogique));
    } else {
      this.subscribeToSaveResponse(this.approchePedagogiqueService.create(approchePedagogique));
    }
  }

  private createFromForm(): IApprochePedagogique {
    return {
      ...new ApprochePedagogique(),
      id: this.editForm.get(['id'])!.value,
      libelleApproche: this.editForm.get(['libelleApproche'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApprochePedagogique>>): void {
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
