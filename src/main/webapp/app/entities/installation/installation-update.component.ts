import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInstallation, Installation } from 'app/shared/model/installation.model';
import { InstallationService } from './installation.service';
import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from 'app/entities/sortie-promotion/sortie-promotion.service';
import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from 'app/entities/activite-installation/activite-installation.service';
import { ISourceFiancement } from 'app/shared/model/source-fiancement.model';
import { SourceFiancementService } from 'app/entities/source-fiancement/source-fiancement.service';

type SelectableEntity = ISortiePromotion | IActiviteInstallation | ISourceFiancement;

type SelectableManyToManyEntity = IActiviteInstallation | ISourceFiancement;

@Component({
  selector: 'jhi-installation-update',
  templateUrl: './installation-update.component.html',
})
export class InstallationUpdateComponent implements OnInit {
  isSaving = false;
  installations: ISortiePromotion[] = [];
  activiteinstallations: IActiviteInstallation[] = [];
  sourcefiancements: ISourceFiancement[] = [];
  dateIntallationDp: any;

  editForm = this.fb.group({
    id: [],
    anneesInstallation: [null, [Validators.required]],
    dateIntallation: [null, [Validators.required]],
    lieuInstallation: [],
    iskit: [],
    installation: [],
    activiteinstallations: [],
    sourceIntallations: [],
  });

  constructor(
    protected installationService: InstallationService,
    protected sortiePromotionService: SortiePromotionService,
    protected activiteInstallationService: ActiviteInstallationService,
    protected sourceFiancementService: SourceFiancementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ installation }) => {
      this.updateForm(installation);

      this.sortiePromotionService
        .query({ filter: 'installation-is-null' })
        .pipe(
          map((res: HttpResponse<ISortiePromotion[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISortiePromotion[]) => {
          if (!installation.installation || !installation.installation.id) {
            this.installations = resBody;
          } else {
            this.sortiePromotionService
              .find(installation.installation.id)
              .pipe(
                map((subRes: HttpResponse<ISortiePromotion>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISortiePromotion[]) => (this.installations = concatRes));
          }
        });

      this.activiteInstallationService
        .query()
        .subscribe((res: HttpResponse<IActiviteInstallation[]>) => (this.activiteinstallations = res.body || []));

      this.sourceFiancementService.query().subscribe((res: HttpResponse<ISourceFiancement[]>) => (this.sourcefiancements = res.body || []));
    });
  }

  updateForm(installation: IInstallation): void {
    this.editForm.patchValue({
      id: installation.id,
      anneesInstallation: installation.anneesInstallation,
      dateIntallation: installation.dateIntallation,
      lieuInstallation: installation.lieuInstallation,
      iskit: installation.iskit,
      installation: installation.installation,
      activiteinstallations: installation.activiteinstallations,
      sourceIntallations: installation.sourceIntallations,
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
      iskit: this.editForm.get(['iskit'])!.value,
      installation: this.editForm.get(['installation'])!.value,
      activiteinstallations: this.editForm.get(['activiteinstallations'])!.value,
      sourceIntallations: this.editForm.get(['sourceIntallations'])!.value,
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

  getSelected(selectedVals: SelectableManyToManyEntity[], option: SelectableManyToManyEntity): SelectableManyToManyEntity {
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
