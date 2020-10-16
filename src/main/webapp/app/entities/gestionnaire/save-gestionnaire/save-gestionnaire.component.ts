import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { GestionnaireService } from '../gestionnaire.service';
import { ActivatedRoute } from '@angular/router';
import { IGestionnaire, Gestionnaire } from 'app/shared/model/gestionnaire.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-gestionnaire',
  templateUrl: './save-gestionnaire.component.html',
  styleUrls: ['./save-gestionnaire.component.scss'],
})
export class SaveGestionnaireComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nomComplet: [null, [Validators.required]],
    contactsGestionnaires: [],
    emploi: [null, [Validators.required]],
  });

  constructor(
    protected gestionnaireService: GestionnaireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gestionnaire }) => {
      this.updateForm(gestionnaire);
    });
  }

  updateForm(gestionnaire: IGestionnaire): void {
    this.editForm.patchValue({
      id: gestionnaire.id,
      nomComplet: gestionnaire.nomComplet,
      contactsGestionnaires: gestionnaire.contactsGestionnaires,
      emploi: gestionnaire.emploi,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gestionnaire = this.createFromForm();
    this.subscribeToSaveResponse(this.gestionnaireService.create(gestionnaire));
    /*  if (gestionnaire.id !== undefined) {
      this.subscribeToSaveResponse(this.gestionnaireService.update(gestionnaire));
    } else {
     
    } */
  }

  private createFromForm(): IGestionnaire {
    return {
      ...new Gestionnaire(),
      id: this.editForm.get(['id'])!.value,
      nomComplet: this.editForm.get(['nomComplet'])!.value,
      contactsGestionnaires: this.editForm.get(['contactsGestionnaires'])!.value,
      emploi: this.editForm.get(['emploi'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGestionnaire>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('gestionnaireListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
