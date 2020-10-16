import { Component, OnInit } from '@angular/core';
import { INiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { Validators, FormBuilder } from '@angular/forms';
import { ApprenantesService } from '../apprenantes.service';
import { NiveauInstructionService } from 'app/entities/niveau-instruction/niveau-instruction.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { IApprenantes, Apprenantes } from 'app/shared/model/apprenantes.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-save-apprenantes',
  templateUrl: './save-apprenantes.component.html',
  styleUrls: ['./save-apprenantes.component.scss'],
})
export class SaveApprenantesComponent implements OnInit {
  isSaving = false;
  niveauinstructions: INiveauInstruction[] = [];
  dateNaissanceDp: any;
  numMatricule: any;
  editForm = this.fb.group({
    id: [],
    matricule: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    dateNaissance: [null, [Validators.required]],
    sexe: [null, [Validators.required]],
    contact: [],
    typecandidat: [],
    situationMatrimonial: [],
    charger: [],
    localite: [],
    niveauapprenant: [],
  });

  constructor(
    protected apprenantesService: ApprenantesService,
    protected niveauInstructionService: NiveauInstructionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apprenantes }) => {
      this.niveauInstructionService
        .query()
        .subscribe((res: HttpResponse<INiveauInstruction[]>) => (this.niveauinstructions = res.body || []));
      this.apprenantesService.getmatricule().subscribe((mat: HttpResponse<any>) => {
        // eslint-disable-next-line no-console
        this.numMatricule = mat.body;
      });
      this.updateForm(apprenantes);
    });
  }

  updateForm(apprenantes: IApprenantes): void {
    this.editForm.patchValue({
      id: apprenantes.id,
      matricule: apprenantes.matricule,
      nom: apprenantes.nom,
      prenom: apprenantes.prenom,
      dateNaissance: apprenantes.dateNaissance,
      sexe: apprenantes.sexe,
      contact: apprenantes.contact,
      typecandidat: apprenantes.typecandidat,
      situationMatrimonial: apprenantes.situationMatrimonial,
      charger: apprenantes.charger,
      localite: apprenantes.localite,
      niveauapprenant: apprenantes.niveauapprenant,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const apprenantes = this.createFromForm();
    if (apprenantes.id !== undefined) {
      this.subscribeToSaveResponse(this.apprenantesService.update(apprenantes));
    } else {
      this.subscribeToSaveResponse(this.apprenantesService.create(apprenantes));
    }
  }

  private createFromForm(): IApprenantes {
    return {
      ...new Apprenantes(),
      id: this.editForm.get(['id'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      typecandidat: this.editForm.get(['typecandidat'])!.value,
      situationMatrimonial: this.editForm.get(['situationMatrimonial'])!.value,
      charger: this.editForm.get(['charger'])!.value,
      localite: this.editForm.get(['localite'])!.value,
      niveauapprenant: this.editForm.get(['niveauapprenant'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApprenantes>>): void {
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

  trackById(index: number, item: INiveauInstruction): any {
    return item.id;
  }
}
