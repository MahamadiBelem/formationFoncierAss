import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { AmenagementComponent } from './amenagement.component';
import { AmenagementDetailComponent } from './amenagement-detail.component';
import { AmenagementUpdateComponent } from './amenagement-update.component';
import { AmenagementDeleteDialogComponent } from './amenagement-delete-dialog.component';
import { amenagementRoute } from './amenagement.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(amenagementRoute)],
  declarations: [AmenagementComponent, AmenagementDetailComponent, AmenagementUpdateComponent, AmenagementDeleteDialogComponent],
  entryComponents: [AmenagementDeleteDialogComponent],
})
export class GestionFormationAmenagementModule {}
