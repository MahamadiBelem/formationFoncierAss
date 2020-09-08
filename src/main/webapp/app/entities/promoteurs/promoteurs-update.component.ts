import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPromoteurs, Promoteurs } from 'app/shared/model/promoteurs.model';
import { PromoteursService } from './promoteurs.service';

@Component({
  selector: 'jhi-promoteurs-update',
  templateUrl: './promoteurs-update.component.html',
})
export class PromoteursUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libellePromoteur: [null, [Validators.required]],
    contactPromoteur: [],
  });

  constructor(protected promoteursService: PromoteursService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promoteurs }) => {
      this.updateForm(promoteurs);
    });
  }

  updateForm(promoteurs: IPromoteurs): void {
    this.editForm.patchValue({
      id: promoteurs.id,
      libellePromoteur: promoteurs.libellePromoteur,
      contactPromoteur: promoteurs.contactPromoteur,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const promoteurs = this.createFromForm();
    if (promoteurs.id !== undefined) {
      this.subscribeToSaveResponse(this.promoteursService.update(promoteurs));
    } else {
      this.subscribeToSaveResponse(this.promoteursService.create(promoteurs));
    }
  }

  private createFromForm(): IPromoteurs {
    return {
      ...new Promoteurs(),
      id: this.editForm.get(['id'])!.value,
      libellePromoteur: this.editForm.get(['libellePromoteur'])!.value,
      contactPromoteur: this.editForm.get(['contactPromoteur'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromoteurs>>): void {
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
