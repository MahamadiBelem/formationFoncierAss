import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IActiviteInstallation, ActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from './activite-installation.service';
import { ICompositionKits } from 'app/shared/model/composition-kits.model';
import { CompositionKitsService } from 'app/entities/composition-kits/composition-kits.service';

@Component({
  selector: 'jhi-activite-installation-update',
  templateUrl: './activite-installation-update.component.html',
})
export class ActiviteInstallationUpdateComponent implements OnInit {
  isSaving = false;
  compositionkits: ICompositionKits[] = [];

  editForm = this.fb.group({
    id: [],
    libelleActivite: [null, [Validators.required]],
    kits: [],
  });

  constructor(
    protected activiteInstallationService: ActiviteInstallationService,
    protected compositionKitsService: CompositionKitsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ activiteInstallation }) => {
      this.updateForm(activiteInstallation);

      this.compositionKitsService.query().subscribe((res: HttpResponse<ICompositionKits[]>) => (this.compositionkits = res.body || []));
    });
  }

  updateForm(activiteInstallation: IActiviteInstallation): void {
    this.editForm.patchValue({
      id: activiteInstallation.id,
      libelleActivite: activiteInstallation.libelleActivite,
      kits: activiteInstallation.kits,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const activiteInstallation = this.createFromForm();
    if (activiteInstallation.id !== undefined) {
      this.subscribeToSaveResponse(this.activiteInstallationService.update(activiteInstallation));
    } else {
      this.subscribeToSaveResponse(this.activiteInstallationService.create(activiteInstallation));
    }
  }

  private createFromForm(): IActiviteInstallation {
    return {
      ...new ActiviteInstallation(),
      id: this.editForm.get(['id'])!.value,
      libelleActivite: this.editForm.get(['libelleActivite'])!.value,
      kits: this.editForm.get(['kits'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActiviteInstallation>>): void {
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

  trackById(index: number, item: ICompositionKits): any {
    return item.id;
  }

  getSelected(selectedVals: ICompositionKits[], option: ICompositionKits): ICompositionKits {
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
