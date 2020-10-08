import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { RegionComponent } from './region.component';
import { RegionDetailComponent } from './region-detail.component';
import { RegionUpdateComponent } from './region-update.component';
import { RegionDeleteDialogComponent } from './region-delete-dialog.component';
import { regionRoute } from './region.route';
import { SaveRegionComponent } from './save-region/save-region.component';
import { UpdateRegionComponent } from './update-region/update-region.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(regionRoute)],
  declarations: [
    RegionComponent,
    RegionDetailComponent,
    RegionUpdateComponent,
    RegionDeleteDialogComponent,
    SaveRegionComponent,
    UpdateRegionComponent,
  ],
  entryComponents: [RegionDeleteDialogComponent],
})
export class GestionFormationRegionModule {}
