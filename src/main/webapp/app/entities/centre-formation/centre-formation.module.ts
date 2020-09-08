import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { CentreFormationComponent } from './centre-formation.component';
import { CentreFormationDetailComponent } from './centre-formation-detail.component';
import { CentreFormationUpdateComponent } from './centre-formation-update.component';
import { CentreFormationDeleteDialogComponent } from './centre-formation-delete-dialog.component';
import { centreFormationRoute } from './centre-formation.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(centreFormationRoute)],
  declarations: [
    CentreFormationComponent,
    CentreFormationDetailComponent,
    CentreFormationUpdateComponent,
    CentreFormationDeleteDialogComponent,
  ],
  entryComponents: [CentreFormationDeleteDialogComponent],
})
export class GestionFormationCentreFormationModule {}
