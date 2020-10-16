import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { SpecialitesComponent } from './specialites.component';
import { SpecialitesDetailComponent } from './specialites-detail.component';
import { SpecialitesUpdateComponent } from './specialites-update.component';
import { SpecialitesDeleteDialogComponent } from './specialites-delete-dialog.component';
import { specialitesRoute } from './specialites.route';
import { SaveSpecialitesComponent } from './save-specialites/save-specialites.component';
import { UpdateSpecialitesComponent } from './update-specialites/update-specialites.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(specialitesRoute)],
  declarations: [
    SpecialitesComponent,
    SpecialitesDetailComponent,
    SpecialitesUpdateComponent,
    SpecialitesDeleteDialogComponent,
    SaveSpecialitesComponent,
    UpdateSpecialitesComponent,
  ],
  entryComponents: [SpecialitesDeleteDialogComponent],
})
export class GestionFormationSpecialitesModule {}
