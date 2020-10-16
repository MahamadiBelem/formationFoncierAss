import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { NiveauRecrutementService } from '../niveau-recrutement.service';
import { ActivatedRoute } from '@angular/router';
import { INiveauRecrutement, NiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-niveau-recrutement',
  templateUrl: './save-niveau-recrutement.component.html',
  styleUrls: ['./save-niveau-recrutement.component.scss'],
})
export class SaveNiveauRecrutementComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleNiveau: [null, [Validators.required]],
  });

  constructor(
    protected niveauRecrutementService: NiveauRecrutementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveauRecrutement }) => {
      this.updateForm(niveauRecrutement);
    });
  }

  updateForm(niveauRecrutement: INiveauRecrutement): void {
    this.editForm.patchValue({
      id: niveauRecrutement.id,
      libelleNiveau: niveauRecrutement.libelleNiveau,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const niveauRecrutement = this.createFromForm();
    this.subscribeToSaveResponse(this.niveauRecrutementService.create(niveauRecrutement));

    /*   if (niveauRecrutement.id !== undefined) {
      this.subscribeToSaveResponse(this.niveauRecrutementService.update(niveauRecrutement));
    } else {
      
    } */
  }

  private createFromForm(): INiveauRecrutement {
    return {
      ...new NiveauRecrutement(),
      id: this.editForm.get(['id'])!.value,
      libelleNiveau: this.editForm.get(['libelleNiveau'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INiveauRecrutement>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('niveauRecrutementListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
  cancel(): void {
    this.activemodale.dismiss();
  }
}
