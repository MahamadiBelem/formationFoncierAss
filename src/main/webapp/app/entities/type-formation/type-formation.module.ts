import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypeFormationComponent } from './type-formation.component';
import { TypeFormationDetailComponent } from './type-formation-detail.component';
import { TypeFormationUpdateComponent } from './type-formation-update.component';
import { TypeFormationDeleteDialogComponent } from './type-formation-delete-dialog.component';
import { typeFormationRoute } from './type-formation.route';
import { SaveTypeFormationComponent } from './save-type-formation/save-type-formation.component';
import { UpdateTypeFormationComponent } from './update-type-formation/update-type-formation.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typeFormationRoute)],
  declarations: [
    TypeFormationComponent,
    TypeFormationDetailComponent,
    TypeFormationUpdateComponent,
    TypeFormationDeleteDialogComponent,
    SaveTypeFormationComponent,
    UpdateTypeFormationComponent,
  ],
  entryComponents: [TypeFormationDeleteDialogComponent],
})
export class GestionFormationTypeFormationModule {}
