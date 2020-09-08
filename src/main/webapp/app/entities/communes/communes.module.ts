import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { CommunesComponent } from './communes.component';
import { CommunesDetailComponent } from './communes-detail.component';
import { CommunesUpdateComponent } from './communes-update.component';
import { CommunesDeleteDialogComponent } from './communes-delete-dialog.component';
import { communesRoute } from './communes.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(communesRoute)],
  declarations: [CommunesComponent, CommunesDetailComponent, CommunesUpdateComponent, CommunesDeleteDialogComponent],
  entryComponents: [CommunesDeleteDialogComponent],
})
export class GestionFormationCommunesModule {}
