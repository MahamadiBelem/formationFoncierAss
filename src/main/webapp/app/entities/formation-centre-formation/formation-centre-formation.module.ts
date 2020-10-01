import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormationCentreFormationComponent } from './formation-centre-formation.component';
import { FormationCentreFormationDetailComponent } from './formation-centre-formation-detail.component';
import { FormationCentreFormationUpdateComponent } from './formation-centre-formation-update.component';
import { FormationCentreFormationDeleteDialogComponent } from './formation-centre-formation-delete-dialog.component';
import { formationCentreFormationRoute } from './formation-centre-formation.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formationCentreFormationRoute)],
  declarations: [
    FormationCentreFormationComponent,
    FormationCentreFormationDetailComponent,
    FormationCentreFormationUpdateComponent,
    FormationCentreFormationDeleteDialogComponent,
  ],
  entryComponents: [FormationCentreFormationDeleteDialogComponent],
})
export class GestionFormationFormationCentreFormationModule {}
