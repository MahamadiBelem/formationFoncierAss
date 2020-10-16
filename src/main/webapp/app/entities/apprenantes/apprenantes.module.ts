import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ApprenantesComponent } from './apprenantes.component';
import { ApprenantesDetailComponent } from './apprenantes-detail.component';
import { ApprenantesUpdateComponent } from './apprenantes-update.component';
import { ApprenantesDeleteDialogComponent } from './apprenantes-delete-dialog.component';
import { apprenantesRoute } from './apprenantes.route';
import { MatriculesComponent } from './matricules/matricules.component';
import { SaveApprenantesComponent } from './save-apprenantes/save-apprenantes.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(apprenantesRoute)],
  declarations: [
    ApprenantesComponent,
    ApprenantesDetailComponent,
    ApprenantesUpdateComponent,
    ApprenantesDeleteDialogComponent,
    MatriculesComponent,
    SaveApprenantesComponent,
  ],
  entryComponents: [ApprenantesDeleteDialogComponent],
})
export class GestionFormationApprenantesModule {}
