import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypecandidat, Typecandidat } from 'app/shared/model/typecandidat.model';
import { TypecandidatService } from './typecandidat.service';

@Component({
  selector: 'jhi-typecandidat-update',
  templateUrl: './typecandidat-update.component.html',
})
export class TypecandidatUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeCandidat: [null, [Validators.required]],
  });

  constructor(protected typecandidatService: TypecandidatService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typecandidat }) => {
      this.updateForm(typecandidat);
    });
  }

  updateForm(typecandidat: ITypecandidat): void {
    this.editForm.patchValue({
      id: typecandidat.id,
      libelleTypeCandidat: typecandidat.libelleTypeCandidat,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typecandidat = this.createFromForm();
    if (typecandidat.id !== undefined) {
      this.subscribeToSaveResponse(this.typecandidatService.update(typecandidat));
    } else {
      this.subscribeToSaveResponse(this.typecandidatService.create(typecandidat));
    }
  }

  private createFromForm(): ITypecandidat {
    return {
      ...new Typecandidat(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeCandidat: this.editForm.get(['libelleTypeCandidat'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypecandidat>>): void {
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
