import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypeAmenagementComponent } from './type-amenagement.component';
import { TypeAmenagementDetailComponent } from './type-amenagement-detail.component';
import { TypeAmenagementUpdateComponent } from './type-amenagement-update.component';
import { TypeAmenagementDeleteDialogComponent } from './type-amenagement-delete-dialog.component';
import { typeAmenagementRoute } from './type-amenagement.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typeAmenagementRoute)],
  declarations: [
    TypeAmenagementComponent,
    TypeAmenagementDetailComponent,
    TypeAmenagementUpdateComponent,
    TypeAmenagementDeleteDialogComponent,
  ],
  entryComponents: [TypeAmenagementDeleteDialogComponent],
})
export class GestionFormationTypeAmenagementModule {}
