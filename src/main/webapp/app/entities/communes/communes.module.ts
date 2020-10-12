import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { CommunesComponent } from './communes.component';
import { CommunesDetailComponent } from './communes-detail.component';
import { CommunesUpdateComponent } from './communes-update.component';
import { CommunesDeleteDialogComponent } from './communes-delete-dialog.component';
import { communesRoute } from './communes.route';
import { SaveCommunesComponent } from './save-communes/save-communes.component';
import { UpdateCommunesComponent } from './update-communes/update-communes.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(communesRoute)],
  declarations: [
    CommunesComponent,
    CommunesDetailComponent,
    CommunesUpdateComponent,
    CommunesDeleteDialogComponent,
    SaveCommunesComponent,
    UpdateCommunesComponent,
  ],
  entryComponents: [CommunesDeleteDialogComponent],
})
export class GestionFormationCommunesModule {}
