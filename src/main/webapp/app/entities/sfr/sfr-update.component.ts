import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISfr, Sfr } from 'app/shared/model/sfr.model';
import { SfrService } from './sfr.service';
import { IStructureLocale } from 'app/shared/model/structure-locale.model';
import { StructureLocaleService } from 'app/entities/structure-locale/structure-locale.service';

@Component({
  selector: 'jhi-sfr-update',
  templateUrl: './sfr-update.component.html',
})
export class SfrUpdateComponent implements OnInit {
  isSaving = false;
  structurelocales: IStructureLocale[] = [];

  editForm = this.fb.group({
    id: [],
    nbrPersonne: [],
    structureLocale: [],
  });

  constructor(
    protected sfrService: SfrService,
    protected structureLocaleService: StructureLocaleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sfr }) => {
      this.updateForm(sfr);

      this.structureLocaleService.query().subscribe((res: HttpResponse<IStructureLocale[]>) => (this.structurelocales = res.body || []));
    });
  }

  updateForm(sfr: ISfr): void {
    this.editForm.patchValue({
      id: sfr.id,
      nbrPersonne: sfr.nbrPersonne,
      structureLocale: sfr.structureLocale,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sfr = this.createFromForm();
    if (sfr.id !== undefined) {
      this.subscribeToSaveResponse(this.sfrService.update(sfr));
    } else {
      this.subscribeToSaveResponse(this.sfrService.create(sfr));
    }
  }

  private createFromForm(): ISfr {
    return {
      ...new Sfr(),
      id: this.editForm.get(['id'])!.value,
      nbrPersonne: this.editForm.get(['nbrPersonne'])!.value,
      structureLocale: this.editForm.get(['structureLocale'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISfr>>): void {
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

  trackById(index: number, item: IStructureLocale): any {
    return item.id;
  }
}
