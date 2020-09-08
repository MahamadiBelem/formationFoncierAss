import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ActiviteInstallationComponent } from './activite-installation.component';
import { ActiviteInstallationDetailComponent } from './activite-installation-detail.component';
import { ActiviteInstallationUpdateComponent } from './activite-installation-update.component';
import { ActiviteInstallationDeleteDialogComponent } from './activite-installation-delete-dialog.component';
import { activiteInstallationRoute } from './activite-installation.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(activiteInstallationRoute)],
  declarations: [
    ActiviteInstallationComponent,
    ActiviteInstallationDetailComponent,
    ActiviteInstallationUpdateComponent,
    ActiviteInstallationDeleteDialogComponent,
  ],
  entryComponents: [ActiviteInstallationDeleteDialogComponent],
})
export class GestionFormationActiviteInstallationModule {}
