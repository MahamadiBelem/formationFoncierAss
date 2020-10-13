import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { FormateursService } from '../formateurs.service';
import { ActivatedRoute } from '@angular/router';
import { IFormateurs, Formateurs } from 'app/shared/model/formateurs.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-formateur',
  templateUrl: './save-formateur.component.html',
  styleUrls: ['./save-formateur.component.scss'],
})
export class SaveFormateurComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nomComplet: [null, [Validators.required]],
    emplois: [null, [Validators.required]],
    contactformateur: [],
  });

  constructor(
    protected formateursService: FormateursService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formateurs }) => {
      this.updateForm(formateurs);
    });
  }

  updateForm(formateurs: IFormateurs): void {
    this.editForm.patchValue({
      id: formateurs.id,
      nomComplet: formateurs.nomComplet,
      emplois: formateurs.emplois,
      contactformateur: formateurs.contactformateur,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formateurs = this.createFromForm();
    this.subscribeToSaveResponse(this.formateursService.create(formateurs));

    /*  if (formateurs.id !== undefined) {
      this.subscribeToSaveResponse(this.formateursService.update(formateurs));
    } else {
     
    } */
  }

  private createFromForm(): IFormateurs {
    return {
      ...new Formateurs(),
      id: this.editForm.get(['id'])!.value,
      nomComplet: this.editForm.get(['nomComplet'])!.value,
      emplois: this.editForm.get(['emplois'])!.value,
      contactformateur: this.editForm.get(['contactformateur'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormateurs>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('formateursListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodal.dismiss();
  }
}
