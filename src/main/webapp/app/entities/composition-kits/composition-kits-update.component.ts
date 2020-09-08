import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompositionKits, CompositionKits } from 'app/shared/model/composition-kits.model';
import { CompositionKitsService } from './composition-kits.service';
import { IKits } from 'app/shared/model/kits.model';
import { KitsService } from 'app/entities/kits/kits.service';

@Component({
  selector: 'jhi-composition-kits-update',
  templateUrl: './composition-kits-update.component.html',
})
export class CompositionKitsUpdateComponent implements OnInit {
  isSaving = false;
  kits: IKits[] = [];

  editForm = this.fb.group({
    id: [],
    libelleKits: [],
    quantiteKits: [],
    kits: [],
  });

  constructor(
    protected compositionKitsService: CompositionKitsService,
    protected kitsService: KitsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compositionKits }) => {
      this.updateForm(compositionKits);

      this.kitsService.query().subscribe((res: HttpResponse<IKits[]>) => (this.kits = res.body || []));
    });
  }

  updateForm(compositionKits: ICompositionKits): void {
    this.editForm.patchValue({
      id: compositionKits.id,
      libelleKits: compositionKits.libelleKits,
      quantiteKits: compositionKits.quantiteKits,
      kits: compositionKits.kits,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compositionKits = this.createFromForm();
    if (compositionKits.id !== undefined) {
      this.subscribeToSaveResponse(this.compositionKitsService.update(compositionKits));
    } else {
      this.subscribeToSaveResponse(this.compositionKitsService.create(compositionKits));
    }
  }

  private createFromForm(): ICompositionKits {
    return {
      ...new CompositionKits(),
      id: this.editForm.get(['id'])!.value,
      libelleKits: this.editForm.get(['libelleKits'])!.value,
      quantiteKits: this.editForm.get(['quantiteKits'])!.value,
      kits: this.editForm.get(['kits'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompositionKits>>): void {
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

  trackById(index: number, item: IKits): any {
    return item.id;
  }

  getSelected(selectedVals: IKits[], option: IKits): IKits {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
