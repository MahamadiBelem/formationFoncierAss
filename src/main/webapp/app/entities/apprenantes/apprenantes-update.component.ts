import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IApprenantes, Apprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from './apprenantes.service';
import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from 'app/entities/sortie-promotion/sortie-promotion.service';

@Component({
  selector: 'jhi-apprenantes-update',
  templateUrl: './apprenantes-update.component.html',
})
export class ApprenantesUpdateComponent implements OnInit {
  isSaving = false;
  sortiepromotions: ISortiePromotion[] = [];
  dateNaissanceDp: any;

  editForm = this.fb.group({
    id: [],
    matricule: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    dateNaissance: [null, [Validators.required]],
    sexe: [null, [Validators.required]],
    contact: [],
    iscandidat: [],
    situationMatrimonial: [],
    charger: [],
    localite: [],
    sortiepromotion: [],
  });

  constructor(
    protected apprenantesService: ApprenantesService,
    protected sortiePromotionService: SortiePromotionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apprenantes }) => {
      this.updateForm(apprenantes);

      this.sortiePromotionService
        .query({ filter: 'apprenantes-is-null' })
        .pipe(
          map((res: HttpResponse<ISortiePromotion[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISortiePromotion[]) => {
          if (!apprenantes.sortiepromotion || !apprenantes.sortiepromotion.id) {
            this.sortiepromotions = resBody;
          } else {
            this.sortiePromotionService
              .find(apprenantes.sortiepromotion.id)
              .pipe(
                map((subRes: HttpResponse<ISortiePromotion>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISortiePromotion[]) => (this.sortiepromotions = concatRes));
          }
        });
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
      iscandidat: apprenantes.iscandidat,
      situationMatrimonial: apprenantes.situationMatrimonial,
      charger: apprenantes.charger,
      localite: apprenantes.localite,
      sortiepromotion: apprenantes.sortiepromotion,
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
      iscandidat: this.editForm.get(['iscandidat'])!.value,
      situationMatrimonial: this.editForm.get(['situationMatrimonial'])!.value,
      charger: this.editForm.get(['charger'])!.value,
      localite: this.editForm.get(['localite'])!.value,
      sortiepromotion: this.editForm.get(['sortiepromotion'])!.value,
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

  trackById(index: number, item: ISortiePromotion): any {
    return item.id;
  }
}
