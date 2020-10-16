import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { GestionnaireComponent } from './gestionnaire.component';
import { GestionnaireDetailComponent } from './gestionnaire-detail.component';
import { GestionnaireUpdateComponent } from './gestionnaire-update.component';
import { GestionnaireDeleteDialogComponent } from './gestionnaire-delete-dialog.component';
import { gestionnaireRoute } from './gestionnaire.route';
import { SaveGestionnaireComponent } from './save-gestionnaire/save-gestionnaire.component';
import { UpdateGestionnaireComponent } from './update-gestionnaire/update-gestionnaire.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(gestionnaireRoute)],
  declarations: [
    GestionnaireComponent,
    GestionnaireDetailComponent,
    GestionnaireUpdateComponent,
    GestionnaireDeleteDialogComponent,
    SaveGestionnaireComponent,
    UpdateGestionnaireComponent,
  ],
  entryComponents: [GestionnaireDeleteDialogComponent],
})
export class GestionFormationGestionnaireModule {}
