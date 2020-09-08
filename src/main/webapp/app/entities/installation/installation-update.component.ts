import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInstallation, Installation } from 'app/shared/model/installation.model';
import { InstallationService } from './installation.service';
import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from 'app/entities/activite-installation/activite-installation.service';
import { IKits } from 'app/shared/model/kits.model';
import { KitsService } from 'app/entities/kits/kits.service';

type SelectableEntity = IActiviteInstallation | IKits;

@Component({
  selector: 'jhi-installation-update',
  templateUrl: './installation-update.component.html',
})
export class InstallationUpdateComponent implements OnInit {
  isSaving = false;
  activiteinstallations: IActiviteInstallation[] = [];
  kits: IKits[] = [];
  dateIntallationDp: any;

  editForm = this.fb.group({
    id: [],
    anneesInstallation: [null, [Validators.required]],
    dateIntallation: [null, [Validators.required]],
    lieuInstallation: [],
    activiteinstallations: [],
    kits: [],
  });

  constructor(
    protected installationService: InstallationService,
    protected activiteInstallationService: ActiviteInstallationService,
    protected kitsService: KitsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ installation }) => {
      this.updateForm(installation);

      this.activiteInstallationService
        .query()
        .subscribe((res: HttpResponse<IActiviteInstallation[]>) => (this.activiteinstallations = res.body || []));

      this.kitsService.query().subscribe((res: HttpResponse<IKits[]>) => (this.kits = res.body || []));
    });
  }

  updateForm(installation: IInstallation): void {
    this.editForm.patchValue({
      id: installation.id,
      anneesInstallation: installation.anneesInstallation,
      dateIntallation: installation.dateIntallation,
      lieuInstallation: installation.lieuInstallation,
      activiteinstallations: installation.activiteinstallations,
      kits: installation.kits,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const installation = this.createFromForm();
    if (installation.id !== undefined) {
      this.subscribeToSaveResponse(this.installationService.update(installation));
    } else {
      this.subscribeToSaveResponse(this.installationService.create(installation));
    }
  }

  private createFromForm(): IInstallation {
    return {
      ...new Installation(),
      id: this.editForm.get(['id'])!.value,
      anneesInstallation: this.editForm.get(['anneesInstallation'])!.value,
      dateIntallation: this.editForm.get(['dateIntallation'])!.value,
      lieuInstallation: this.editForm.get(['lieuInstallation'])!.value,
      activiteinstallations: this.editForm.get(['activiteinstallations'])!.value,
      kits: this.editForm.get(['kits'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstallation>>): void {
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

  getSelected(selectedVals: SelectableEntity[], option: SelectableEntity): SelectableEntity {
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
