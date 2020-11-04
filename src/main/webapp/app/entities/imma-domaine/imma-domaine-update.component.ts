import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IImmaDomaine, ImmaDomaine } from 'app/shared/model/imma-domaine.model';
import { ImmaDomaineService } from './imma-domaine.service';
import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from 'app/entities/sfr/sfr.service';

@Component({
  selector: 'jhi-imma-domaine-update',
  templateUrl: './imma-domaine-update.component.html',
})
export class ImmaDomaineUpdateComponent implements OnInit {
  isSaving = false;
  sfrs: ISfr[] = [];

  editForm = this.fb.group({
    id: [],
    annee: [],
    superficeTotInventorie: [],
    superficieTotImmatricule: [],
    superficieTotEncadre: [],
    sfrImmaDomaine: [],
  });

  constructor(
    protected immaDomaineService: ImmaDomaineService,
    protected sfrService: SfrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ immaDomaine }) => {
      this.updateForm(immaDomaine);

      this.sfrService.query().subscribe((res: HttpResponse<ISfr[]>) => (this.sfrs = res.body || []));
    });
  }

  updateForm(immaDomaine: IImmaDomaine): void {
    this.editForm.patchValue({
      id: immaDomaine.id,
      annee: immaDomaine.annee,
      superficeTotInventorie: immaDomaine.superficeTotInventorie,
      superficieTotImmatricule: immaDomaine.superficieTotImmatricule,
      superficieTotEncadre: immaDomaine.superficieTotEncadre,
      sfrImmaDomaine: immaDomaine.sfrImmaDomaine,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const immaDomaine = this.createFromForm();
    if (immaDomaine.id !== undefined) {
      this.subscribeToSaveResponse(this.immaDomaineService.update(immaDomaine));
    } else {
      this.subscribeToSaveResponse(this.immaDomaineService.create(immaDomaine));
    }
  }

  private createFromForm(): IImmaDomaine {
    return {
      ...new ImmaDomaine(),
      id: this.editForm.get(['id'])!.value,
      annee: this.editForm.get(['annee'])!.value,
      superficeTotInventorie: this.editForm.get(['superficeTotInventorie'])!.value,
      superficieTotImmatricule: this.editForm.get(['superficieTotImmatricule'])!.value,
      superficieTotEncadre: this.editForm.get(['superficieTotEncadre'])!.value,
      sfrImmaDomaine: this.editForm.get(['sfrImmaDomaine'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImmaDomaine>>): void {
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

  trackById(index: number, item: ISfr): any {
    return item.id;
  }
}
