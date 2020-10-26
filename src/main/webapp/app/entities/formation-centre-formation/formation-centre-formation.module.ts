import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormationCentreFormationComponent } from './formation-centre-formation.component';
import { FormationCentreFormationDetailComponent } from './formation-centre-formation-detail.component';
import { FormationCentreFormationUpdateComponent } from './formation-centre-formation-update.component';
import { FormationCentreFormationDeleteDialogComponent } from './formation-centre-formation-delete-dialog.component';
import { formationCentreFormationRoute } from './formation-centre-formation.route';
import { SaveFormationCentreComponent } from './save-formation-centre/save-formation-centre.component';
import { UpdateFormationCentreComponent } from './update-formation-centre/update-formation-centre.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formationCentreFormationRoute)],
  declarations: [
    FormationCentreFormationComponent,
    FormationCentreFormationDetailComponent,
    FormationCentreFormationUpdateComponent,
    FormationCentreFormationDeleteDialogComponent,
    SaveFormationCentreComponent,
    UpdateFormationCentreComponent,
  ],
  entryComponents: [FormationCentreFormationDeleteDialogComponent],
})
export class GestionFormationFormationCentreFormationModule {}
