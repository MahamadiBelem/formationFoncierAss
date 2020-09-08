import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICentreFormation, CentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from './centre-formation.service';
import { ICommunes } from 'app/shared/model/communes.model';
import { CommunesService } from 'app/entities/communes/communes.service';
import { IPromoteurs } from 'app/shared/model/promoteurs.model';
import { PromoteursService } from 'app/entities/promoteurs/promoteurs.service';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';
import { GestionnaireService } from 'app/entities/gestionnaire/gestionnaire.service';
import { IApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { ApprochePedagogiqueService } from 'app/entities/approche-pedagogique/approche-pedagogique.service';
import { IPublicCible } from 'app/shared/model/public-cible.model';
import { PublicCibleService } from 'app/entities/public-cible/public-cible.service';
import { ISpecialites } from 'app/shared/model/specialites.model';
import { SpecialitesService } from 'app/entities/specialites/specialites.service';
import { IDomaineFormation } from 'app/shared/model/domaine-formation.model';
import { DomaineFormationService } from 'app/entities/domaine-formation/domaine-formation.service';
import { IContributions } from 'app/shared/model/contributions.model';
import { ContributionsService } from 'app/entities/contributions/contributions.service';
import { INiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { NiveauRecrutementService } from 'app/entities/niveau-recrutement/niveau-recrutement.service';
import { IFormateurs } from 'app/shared/model/formateurs.model';
import { FormateursService } from 'app/entities/formateurs/formateurs.service';
import { IConditionAccess } from 'app/shared/model/condition-access.model';
import { ConditionAccessService } from 'app/entities/condition-access/condition-access.service';
import { IFormations } from 'app/shared/model/formations.model';
import { FormationsService } from 'app/entities/formations/formations.service';

type SelectableEntity =
  | ICommunes
  | IPromoteurs
  | IGestionnaire
  | IApprochePedagogique
  | IPublicCible
  | ISpecialites
  | IDomaineFormation
  | IContributions
  | INiveauRecrutement
  | IFormateurs
  | IConditionAccess
  | IFormations;

type SelectableManyToManyEntity =
  | IApprochePedagogique
  | IPublicCible
  | ISpecialites
  | IDomaineFormation
  | IContributions
  | INiveauRecrutement
  | IFormateurs
  | IConditionAccess
  | IFormations;

@Component({
  selector: 'jhi-centre-formation-update',
  templateUrl: './centre-formation-update.component.html',
})
export class CentreFormationUpdateComponent implements OnInit {
  isSaving = false;
  communes: ICommunes[] = [];
  promoteurs: IPromoteurs[] = [];
  gestionnaires: IGestionnaire[] = [];
  approchepedagogiques: IApprochePedagogique[] = [];
  publiccibles: IPublicCible[] = [];
  specialites: ISpecialites[] = [];
  domaineformations: IDomaineFormation[] = [];
  contributions: IContributions[] = [];
  niveaurecrutements: INiveauRecrutement[] = [];
  formateurs: IFormateurs[] = [];
  conditionaccesses: IConditionAccess[] = [];
  formations: IFormations[] = [];
  dateOuvertureDp: any;

  editForm = this.fb.group({
    id: [],
    denomination: [null, [Validators.required]],
    localisation: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    statuts: [],
    capaciteacceuil: [],
    refOuverture: [],
    dateOuverture: [],
    regime: [],
    communes: [],
    promoteurs: [],
    gestionnaires: [],
    approchepedagogiques: [],
    publiccibles: [],
    specialites: [],
    domaineformations: [],
    contributions: [],
    niveaurecrutements: [],
    formateurs: [],
    conditionaccesses: [],
    formations: [],
  });

  constructor(
    protected centreFormationService: CentreFormationService,
    protected communesService: CommunesService,
    protected promoteursService: PromoteursService,
    protected gestionnaireService: GestionnaireService,
    protected approchePedagogiqueService: ApprochePedagogiqueService,
    protected publicCibleService: PublicCibleService,
    protected specialitesService: SpecialitesService,
    protected domaineFormationService: DomaineFormationService,
    protected contributionsService: ContributionsService,
    protected niveauRecrutementService: NiveauRecrutementService,
    protected formateursService: FormateursService,
    protected conditionAccessService: ConditionAccessService,
    protected formationsService: FormationsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centreFormation }) => {
      this.updateForm(centreFormation);

      this.communesService.query().subscribe((res: HttpResponse<ICommunes[]>) => (this.communes = res.body || []));

      this.promoteursService.query().subscribe((res: HttpResponse<IPromoteurs[]>) => (this.promoteurs = res.body || []));

      this.gestionnaireService.query().subscribe((res: HttpResponse<IGestionnaire[]>) => (this.gestionnaires = res.body || []));

      this.approchePedagogiqueService
        .query()
        .subscribe((res: HttpResponse<IApprochePedagogique[]>) => (this.approchepedagogiques = res.body || []));

      this.publicCibleService.query().subscribe((res: HttpResponse<IPublicCible[]>) => (this.publiccibles = res.body || []));

      this.specialitesService.query().subscribe((res: HttpResponse<ISpecialites[]>) => (this.specialites = res.body || []));

      this.domaineFormationService.query().subscribe((res: HttpResponse<IDomaineFormation[]>) => (this.domaineformations = res.body || []));

      this.contributionsService.query().subscribe((res: HttpResponse<IContributions[]>) => (this.contributions = res.body || []));

      this.niveauRecrutementService
        .query()
        .subscribe((res: HttpResponse<INiveauRecrutement[]>) => (this.niveaurecrutements = res.body || []));

      this.formateursService.query().subscribe((res: HttpResponse<IFormateurs[]>) => (this.formateurs = res.body || []));

      this.conditionAccessService.query().subscribe((res: HttpResponse<IConditionAccess[]>) => (this.conditionaccesses = res.body || []));

      this.formationsService.query().subscribe((res: HttpResponse<IFormations[]>) => (this.formations = res.body || []));
    });
  }

  updateForm(centreFormation: ICentreFormation): void {
    this.editForm.patchValue({
      id: centreFormation.id,
      denomination: centreFormation.denomination,
      localisation: centreFormation.localisation,
      adresse: centreFormation.adresse,
      statuts: centreFormation.statuts,
      capaciteacceuil: centreFormation.capaciteacceuil,
      refOuverture: centreFormation.refOuverture,
      dateOuverture: centreFormation.dateOuverture,
      regime: centreFormation.regime,
      communes: centreFormation.communes,
      promoteurs: centreFormation.promoteurs,
      gestionnaires: centreFormation.gestionnaires,
      approchepedagogiques: centreFormation.approchepedagogiques,
      publiccibles: centreFormation.publiccibles,
      specialites: centreFormation.specialites,
      domaineformations: centreFormation.domaineformations,
      contributions: centreFormation.contributions,
      niveaurecrutements: centreFormation.niveaurecrutements,
      formateurs: centreFormation.formateurs,
      conditionaccesses: centreFormation.conditionaccesses,
      formations: centreFormation.formations,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const centreFormation = this.createFromForm();
    if (centreFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.centreFormationService.update(centreFormation));
    } else {
      this.subscribeToSaveResponse(this.centreFormationService.create(centreFormation));
    }
  }

  private createFromForm(): ICentreFormation {
    return {
      ...new CentreFormation(),
      id: this.editForm.get(['id'])!.value,
      denomination: this.editForm.get(['denomination'])!.value,
      localisation: this.editForm.get(['localisation'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      statuts: this.editForm.get(['statuts'])!.value,
      capaciteacceuil: this.editForm.get(['capaciteacceuil'])!.value,
      refOuverture: this.editForm.get(['refOuverture'])!.value,
      dateOuverture: this.editForm.get(['dateOuverture'])!.value,
      regime: this.editForm.get(['regime'])!.value,
      communes: this.editForm.get(['communes'])!.value,
      promoteurs: this.editForm.get(['promoteurs'])!.value,
      gestionnaires: this.editForm.get(['gestionnaires'])!.value,
      approchepedagogiques: this.editForm.get(['approchepedagogiques'])!.value,
      publiccibles: this.editForm.get(['publiccibles'])!.value,
      specialites: this.editForm.get(['specialites'])!.value,
      domaineformations: this.editForm.get(['domaineformations'])!.value,
      contributions: this.editForm.get(['contributions'])!.value,
      niveaurecrutements: this.editForm.get(['niveaurecrutements'])!.value,
      formateurs: this.editForm.get(['formateurs'])!.value,
      conditionaccesses: this.editForm.get(['conditionaccesses'])!.value,
      formations: this.editForm.get(['formations'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICentreFormation>>): void {
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
