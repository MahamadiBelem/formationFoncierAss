import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { VillagesComponent } from './villages.component';
import { VillagesDetailComponent } from './villages-detail.component';
import { VillagesUpdateComponent } from './villages-update.component';
import { VillagesDeleteDialogComponent } from './villages-delete-dialog.component';
import { villagesRoute } from './villages.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(villagesRoute)],
  declarations: [VillagesComponent, VillagesDetailComponent, VillagesUpdateComponent, VillagesDeleteDialogComponent],
  entryComponents: [VillagesDeleteDialogComponent],
})
export class GestionFormationVillagesModule {}
