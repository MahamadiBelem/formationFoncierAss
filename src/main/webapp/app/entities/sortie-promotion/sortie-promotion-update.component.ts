import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISortiePromotion, SortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from './sortie-promotion.service';
import { IInscription } from 'app/shared/model/inscription.model';
import { InscriptionService } from 'app/entities/inscription/inscription.service';

@Component({
  selector: 'jhi-sortie-promotion-update',
  templateUrl: './sortie-promotion-update.component.html',
})
export class SortiePromotionUpdateComponent implements OnInit {
  isSaving = false;
  sortiepromotions: IInscription[] = [];
  dateSortieDp: any;

  editForm = this.fb.group({
    id: [],
    dateSortie: [null, [Validators.required]],
    anneesSortie: [null, [Validators.required]],
    motif: [],
    issortie: [],
    sortiepromotion: [],
  });

  constructor(
    protected sortiePromotionService: SortiePromotionService,
    protected inscriptionService: InscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sortiePromotion }) => {
      this.updateForm(sortiePromotion);

      this.inscriptionService
        .query({ filter: 'sortiepromotion-is-null' })
        .pipe(
          map((res: HttpResponse<IInscription[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInscription[]) => {
          if (!sortiePromotion.sortiepromotion || !sortiePromotion.sortiepromotion.id) {
            this.sortiepromotions = resBody;
          } else {
            this.inscriptionService
              .find(sortiePromotion.sortiepromotion.id)
              .pipe(
                map((subRes: HttpResponse<IInscription>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInscription[]) => (this.sortiepromotions = concatRes));
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
      issortie: sortiePromotion.issortie,
      sortiepromotion: sortiePromotion.sortiepromotion,
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
      issortie: this.editForm.get(['issortie'])!.value,
      sortiepromotion: this.editForm.get(['sortiepromotion'])!.value,
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

  trackById(index: number, item: IInscription): any {
    return item.id;
  }
}
