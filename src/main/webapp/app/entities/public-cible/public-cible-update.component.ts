import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPublicCible, PublicCible } from 'app/shared/model/public-cible.model';
import { PublicCibleService } from './public-cible.service';

@Component({
  selector: 'jhi-public-cible-update',
  templateUrl: './public-cible-update.component.html',
})
export class PublicCibleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libellePublic: [null, [Validators.required]],
  });

  constructor(protected publicCibleService: PublicCibleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ publicCible }) => {
      this.updateForm(publicCible);
    });
  }

  updateForm(publicCible: IPublicCible): void {
    this.editForm.patchValue({
      id: publicCible.id,
      libellePublic: publicCible.libellePublic,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const publicCible = this.createFromForm();
    if (publicCible.id !== undefined) {
      this.subscribeToSaveResponse(this.publicCibleService.update(publicCible));
    } else {
      this.subscribeToSaveResponse(this.publicCibleService.create(publicCible));
    }
  }

  private createFromForm(): IPublicCible {
    return {
      ...new PublicCible(),
      id: this.editForm.get(['id'])!.value,
      libellePublic: this.editForm.get(['libellePublic'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPublicCible>>): void {
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
