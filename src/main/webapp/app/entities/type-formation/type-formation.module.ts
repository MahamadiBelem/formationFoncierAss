import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypeFormationComponent } from './type-formation.component';
import { TypeFormationDetailComponent } from './type-formation-detail.component';
import { TypeFormationUpdateComponent } from './type-formation-update.component';
import { TypeFormationDeleteDialogComponent } from './type-formation-delete-dialog.component';
import { typeFormationRoute } from './type-formation.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typeFormationRoute)],
  declarations: [TypeFormationComponent, TypeFormationDetailComponent, TypeFormationUpdateComponent, TypeFormationDeleteDialogComponent],
  entryComponents: [TypeFormationDeleteDialogComponent],
})
export class GestionFormationTypeFormationModule {}
