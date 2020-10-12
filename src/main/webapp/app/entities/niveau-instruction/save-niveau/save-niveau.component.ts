import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { NiveauInstructionService } from '../niveau-instruction.service';
import { ActivatedRoute } from '@angular/router';
import { INiveauInstruction, NiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-niveau',
  templateUrl: './save-niveau.component.html',
  styleUrls: ['./save-niveau.component.scss'],
})
export class SaveNiveauComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    niveauInstruction: [null, [Validators.required]],
  });

  constructor(
    protected niveauInstructionService: NiveauInstructionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodal: NgbActiveModal,
    private eventManager: JhiEventManager
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
    this.subscribeToSaveResponse(this.niveauInstructionService.create(niveauInstruction));
    /*
    if (niveauInstruction.id !== undefined) {
      this.subscribeToSaveResponse(this.niveauInstructionService.update(niveauInstruction));
    } else {
      
    }

    */
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
    this.eventManager.broadcast('niveauInstructionListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodal.dismiss();
  }
}
