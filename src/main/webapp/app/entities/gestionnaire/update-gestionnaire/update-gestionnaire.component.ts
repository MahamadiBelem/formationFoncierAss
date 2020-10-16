import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { GestionnaireService } from '../gestionnaire.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IGestionnaire, Gestionnaire } from 'app/shared/model/gestionnaire.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-gestionnaire',
  templateUrl: './update-gestionnaire.component.html',
  styleUrls: ['./update-gestionnaire.component.scss'],
})
export class UpdateGestionnaireComponent implements OnInit {
  @Input() public gestionnaire: any;
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
    this.updateForm(this.gestionnaire);
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

    if (gestionnaire.id !== undefined) {
      this.subscribeToSaveResponse(this.gestionnaireService.update(gestionnaire));
    } else {
      this.subscribeToSaveResponse(this.gestionnaireService.create(gestionnaire));
    }
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
