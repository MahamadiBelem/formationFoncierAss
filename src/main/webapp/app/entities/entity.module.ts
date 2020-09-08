import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.GestionFormationRegionModule),
      },
      {
        path: 'provinces',
        loadChildren: () => import('./provinces/provinces.module').then(m => m.GestionFormationProvincesModule),
      },
      {
        path: 'communes',
        loadChildren: () => import('./communes/communes.module').then(m => m.GestionFormationCommunesModule),
      },
      {
        path: 'villages',
        loadChildren: () => import('./villages/villages.module').then(m => m.GestionFormationVillagesModule),
      },
      {
        path: 'condition-access',
        loadChildren: () => import('./condition-access/condition-access.module').then(m => m.GestionFormationConditionAccessModule),
      },
      {
        path: 'approche-pedagogique',
        loadChildren: () =>
          import('./approche-pedagogique/approche-pedagogique.module').then(m => m.GestionFormationApprochePedagogiqueModule),
      },
      {
        path: 'specialites',
        loadChildren: () => import('./specialites/specialites.module').then(m => m.GestionFormationSpecialitesModule),
      },
      {
        path: 'domaine-formation',
        loadChildren: () => import('./domaine-formation/domaine-formation.module').then(m => m.GestionFormationDomaineFormationModule),
      },
      {
        path: 'contributions',
        loadChildren: () => import('./contributions/contributions.module').then(m => m.GestionFormationContributionsModule),
      },
      {
        path: 'gestionnaire',
        loadChildren: () => import('./gestionnaire/gestionnaire.module').then(m => m.GestionFormationGestionnaireModule),
      },
      {
        path: 'promoteurs',
        loadChildren: () => import('./promoteurs/promoteurs.module').then(m => m.GestionFormationPromoteursModule),
      },
      {
        path: 'public-cible',
        loadChildren: () => import('./public-cible/public-cible.module').then(m => m.GestionFormationPublicCibleModule),
      },
      {
        path: 'niveau-recrutement',
        loadChildren: () => import('./niveau-recrutement/niveau-recrutement.module').then(m => m.GestionFormationNiveauRecrutementModule),
      },
      {
        path: 'centre-formation',
        loadChildren: () => import('./centre-formation/centre-formation.module').then(m => m.GestionFormationCentreFormationModule),
      },
      {
        path: 'type-infratructure',
        loadChildren: () => import('./type-infratructure/type-infratructure.module').then(m => m.GestionFormationTypeInfratructureModule),
      },
      {
        path: 'type-amenagement',
        loadChildren: () => import('./type-amenagement/type-amenagement.module').then(m => m.GestionFormationTypeAmenagementModule),
      },
      {
        path: 'infrastructure',
        loadChildren: () => import('./infrastructure/infrastructure.module').then(m => m.GestionFormationInfrastructureModule),
      },
      {
        path: 'amenagement',
        loadChildren: () => import('./amenagement/amenagement.module').then(m => m.GestionFormationAmenagementModule),
      },
      {
        path: 'formateurs',
        loadChildren: () => import('./formateurs/formateurs.module').then(m => m.GestionFormationFormateursModule),
      },
      {
        path: 'apprenantes',
        loadChildren: () => import('./apprenantes/apprenantes.module').then(m => m.GestionFormationApprenantesModule),
      },
      {
        path: 'formations',
        loadChildren: () => import('./formations/formations.module').then(m => m.GestionFormationFormationsModule),
      },
      {
        path: 'niveau-instruction',
        loadChildren: () => import('./niveau-instruction/niveau-instruction.module').then(m => m.GestionFormationNiveauInstructionModule),
      },
      {
        path: 'type-formation',
        loadChildren: () => import('./type-formation/type-formation.module').then(m => m.GestionFormationTypeFormationModule),
      },
      {
        path: 'sortie-promotion',
        loadChildren: () => import('./sortie-promotion/sortie-promotion.module').then(m => m.GestionFormationSortiePromotionModule),
      },
      {
        path: 'installation',
        loadChildren: () => import('./installation/installation.module').then(m => m.GestionFormationInstallationModule),
      },
      {
        path: 'activite-installation',
        loadChildren: () =>
          import('./activite-installation/activite-installation.module').then(m => m.GestionFormationActiviteInstallationModule),
      },
      {
        path: 'kits',
        loadChildren: () => import('./kits/kits.module').then(m => m.GestionFormationKitsModule),
      },
      {
        path: 'composition-kits',
        loadChildren: () => import('./composition-kits/composition-kits.module').then(m => m.GestionFormationCompositionKitsModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GestionFormationEntityModule {}
