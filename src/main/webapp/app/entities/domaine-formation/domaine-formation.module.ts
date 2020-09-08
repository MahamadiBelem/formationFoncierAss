import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { DomaineFormationComponent } from './domaine-formation.component';
import { DomaineFormationDetailComponent } from './domaine-formation-detail.component';
import { DomaineFormationUpdateComponent } from './domaine-formation-update.component';
import { DomaineFormationDeleteDialogComponent } from './domaine-formation-delete-dialog.component';
import { domaineFormationRoute } from './domaine-formation.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(domaineFormationRoute)],
  declarations: [
    DomaineFormationComponent,
    DomaineFormationDetailComponent,
    DomaineFormationUpdateComponent,
    DomaineFormationDeleteDialogComponent,
  ],
  entryComponents: [DomaineFormationDeleteDialogComponent],
})
export class GestionFormationDomaineFormationModule {}
