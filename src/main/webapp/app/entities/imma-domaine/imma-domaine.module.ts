import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ImmaDomaineComponent } from './imma-domaine.component';
import { ImmaDomaineDetailComponent } from './imma-domaine-detail.component';
import { ImmaDomaineUpdateComponent } from './imma-domaine-update.component';
import { ImmaDomaineDeleteDialogComponent } from './imma-domaine-delete-dialog.component';
import { immaDomaineRoute } from './imma-domaine.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(immaDomaineRoute)],
  declarations: [ImmaDomaineComponent, ImmaDomaineDetailComponent, ImmaDomaineUpdateComponent, ImmaDomaineDeleteDialogComponent],
  entryComponents: [ImmaDomaineDeleteDialogComponent],
})
export class GestionFormationImmaDomaineModule {}
