import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { SpecialitesComponent } from './specialites.component';
import { SpecialitesDetailComponent } from './specialites-detail.component';
import { SpecialitesUpdateComponent } from './specialites-update.component';
import { SpecialitesDeleteDialogComponent } from './specialites-delete-dialog.component';
import { specialitesRoute } from './specialites.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(specialitesRoute)],
  declarations: [SpecialitesComponent, SpecialitesDetailComponent, SpecialitesUpdateComponent, SpecialitesDeleteDialogComponent],
  entryComponents: [SpecialitesDeleteDialogComponent],
})
export class GestionFormationSpecialitesModule {}
