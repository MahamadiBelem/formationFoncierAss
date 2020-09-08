import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { InstallationComponent } from './installation.component';
import { InstallationDetailComponent } from './installation-detail.component';
import { InstallationUpdateComponent } from './installation-update.component';
import { InstallationDeleteDialogComponent } from './installation-delete-dialog.component';
import { installationRoute } from './installation.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(installationRoute)],
  declarations: [InstallationComponent, InstallationDetailComponent, InstallationUpdateComponent, InstallationDeleteDialogComponent],
  entryComponents: [InstallationDeleteDialogComponent],
})
export class GestionFormationInstallationModule {}
