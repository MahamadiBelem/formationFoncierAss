import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { DomaineFormationComponent } from './domaine-formation.component';
import { DomaineFormationDetailComponent } from './domaine-formation-detail.component';
import { DomaineFormationUpdateComponent } from './domaine-formation-update.component';
import { DomaineFormationDeleteDialogComponent } from './domaine-formation-delete-dialog.component';
import { domaineFormationRoute } from './domaine-formation.route';
import { SaveDomaineFormationComponent } from './save-domaine-formation/save-domaine-formation.component';
import { UpdateDomaineFormationComponent } from './update-domaine-formation/update-domaine-formation.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(domaineFormationRoute)],
  declarations: [
    DomaineFormationComponent,
    DomaineFormationDetailComponent,
    DomaineFormationUpdateComponent,
    DomaineFormationDeleteDialogComponent,
    SaveDomaineFormationComponent,
    UpdateDomaineFormationComponent,
  ],
  entryComponents: [DomaineFormationDeleteDialogComponent],
})
export class GestionFormationDomaineFormationModule {}
