import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IKits, Kits } from 'app/shared/model/kits.model';
import { KitsService } from './kits.service';

@Component({
  selector: 'jhi-kits-update',
  templateUrl: './kits-update.component.html',
})
export class KitsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    isobtenu: [],
  });

  constructor(protected kitsService: KitsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kits }) => {
      this.updateForm(kits);
    });
  }

  updateForm(kits: IKits): void {
    this.editForm.patchValue({
      id: kits.id,
      isobtenu: kits.isobtenu,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const kits = this.createFromForm();
    if (kits.id !== undefined) {
      this.subscribeToSaveResponse(this.kitsService.update(kits));
    } else {
      this.subscribeToSaveResponse(this.kitsService.create(kits));
    }
  }

  private createFromForm(): IKits {
    return {
      ...new Kits(),
      id: this.editForm.get(['id'])!.value,
      isobtenu: this.editForm.get(['isobtenu'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKits>>): void {
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
