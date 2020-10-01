import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INiveauInstruction, NiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { NiveauInstructionService } from './niveau-instruction.service';

@Component({
  selector: 'jhi-niveau-instruction-update',
  templateUrl: './niveau-instruction-update.component.html',
})
export class NiveauInstructionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    niveauInstruction: [null, [Validators.required]],
  });

  constructor(
    protected niveauInstructionService: NiveauInstructionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveauInstruction }) => {
      this.updateForm(niveauInstruction);
    });
  }

  updateForm(niveauInstruction: INiveauInstruction): void {
    this.editForm.patchValue({
      id: niveauInstruction.id,
      niveauInstruction: niveauInstruction.niveauInstruction,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const niveauInstruction = this.createFromForm();
    if (niveauInstruction.id !== undefined) {
      this.subscribeToSaveResponse(this.niveauInstructionService.update(niveauInstruction));
    } else {
      this.subscribeToSaveResponse(this.niveauInstructionService.create(niveauInstruction));
    }
  }

  private createFromForm(): INiveauInstruction {
    return {
      ...new NiveauInstruction(),
      id: this.editForm.get(['id'])!.value,
      niveauInstruction: this.editForm.get(['niveauInstruction'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INiveauInstruction>>): void {
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
