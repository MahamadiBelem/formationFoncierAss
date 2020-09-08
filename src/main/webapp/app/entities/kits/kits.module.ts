import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { KitsComponent } from './kits.component';
import { KitsDetailComponent } from './kits-detail.component';
import { KitsUpdateComponent } from './kits-update.component';
import { KitsDeleteDialogComponent } from './kits-delete-dialog.component';
import { kitsRoute } from './kits.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(kitsRoute)],
  declarations: [KitsComponent, KitsDetailComponent, KitsUpdateComponent, KitsDeleteDialogComponent],
  entryComponents: [KitsDeleteDialogComponent],
})
export class GestionFormationKitsModule {}
