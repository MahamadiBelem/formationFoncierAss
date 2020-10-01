import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISortiePromotion, SortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from './sortie-promotion.service';
import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from 'app/entities/apprenantes/apprenantes.service';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';

type SelectableEntity = IApprenantes | ICentreFormation;

@Component({
  selector: 'jhi-sortie-promotion-update',
  templateUrl: './sortie-promotion-update.component.html',
})
export class SortiePromotionUpdateComponent implements OnInit {
  isSaving = false;
  sortiepromotions: IApprenantes[] = [];
  sortiecentreformations: ICentreFormation[] = [];
  dateSortieDp: any;

  editForm = this.fb.group({
    id: [],
    dateSortie: [null, [Validators.required]],
    anneesSortie: [null, [Validators.required]],
    motif: [],
    sortiepromotion: [],
    sortieCentreFormation: [],
  });

  constructor(
    protected sortiePromotionService: SortiePromotionService,
    protected apprenantesService: ApprenantesService,
    protected centreFormationService: CentreFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sortiePromotion }) => {
      this.updateForm(sortiePromotion);

      this.apprenantesService
        .query({ filter: 'sortiepromotion-is-null' })
        .pipe(
          map((res: HttpResponse<IApprenantes[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IApprenantes[]) => {
          if (!sortiePromotion.sortiepromotion || !sortiePromotion.sortiepromotion.id) {
            this.sortiepromotions = resBody;
          } else {
            this.apprenantesService
              .find(sortiePromotion.sortiepromotion.id)
              .pipe(
                map((subRes: HttpResponse<IApprenantes>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IApprenantes[]) => (this.sortiepromotions = concatRes));
          }
        });

      this.centreFormationService
        .query({ filter: 'sortiepromotion-is-null' })
        .pipe(
          map((res: HttpResponse<ICentreFormation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICentreFormation[]) => {
          if (!sortiePromotion.sortieCentreFormation || !sortiePromotion.sortieCentreFormation.id) {
            this.sortiecentreformations = resBody;
          } else {
            this.centreFormationService
              .find(sortiePromotion.sortieCentreFormation.id)
              .pipe(
                map((subRes: HttpResponse<ICentreFormation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICentreFormation[]) => (this.sortiecentreformations = concatRes));
          }
        });
    });
  }

  updateForm(sortiePromotion: ISortiePromotion): void {
    this.editForm.patchValue({
      id: sortiePromotion.id,
      dateSortie: sortiePromotion.dateSortie,
      anneesSortie: sortiePromotion.anneesSortie,
      motif: sortiePromotion.motif,
      sortiepromotion: sortiePromotion.sortiepromotion,
      sortieCentreFormation: sortiePromotion.sortieCentreFormation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sortiePromotion = this.createFromForm();
    if (sortiePromotion.id !== undefined) {
      this.subscribeToSaveResponse(this.sortiePromotionService.update(sortiePromotion));
    } else {
      this.subscribeToSaveResponse(this.sortiePromotionService.create(sortiePromotion));
    }
  }

  private createFromForm(): ISortiePromotion {
    return {
      ...new SortiePromotion(),
      id: this.editForm.get(['id'])!.value,
      dateSortie: this.editForm.get(['dateSortie'])!.value,
      anneesSortie: this.editForm.get(['anneesSortie'])!.value,
      motif: this.editForm.get(['motif'])!.value,
      sortiepromotion: this.editForm.get(['sortiepromotion'])!.value,
      sortieCentreFormation: this.editForm.get(['sortieCentreFormation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISortiePromotion>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
