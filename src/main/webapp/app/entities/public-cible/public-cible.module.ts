import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { PublicCibleComponent } from './public-cible.component';
import { PublicCibleDetailComponent } from './public-cible-detail.component';
import { PublicCibleUpdateComponent } from './public-cible-update.component';
import { PublicCibleDeleteDialogComponent } from './public-cible-delete-dialog.component';
import { publicCibleRoute } from './public-cible.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(publicCibleRoute)],
  declarations: [PublicCibleComponent, PublicCibleDetailComponent, PublicCibleUpdateComponent, PublicCibleDeleteDialogComponent],
  entryComponents: [PublicCibleDeleteDialogComponent],
})
export class GestionFormationPublicCibleModule {}
