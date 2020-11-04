import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypeDemandeurComponent } from './type-demandeur.component';
import { TypeDemandeurDetailComponent } from './type-demandeur-detail.component';
import { TypeDemandeurUpdateComponent } from './type-demandeur-update.component';
import { TypeDemandeurDeleteDialogComponent } from './type-demandeur-delete-dialog.component';
import { typeDemandeurRoute } from './type-demandeur.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typeDemandeurRoute)],
  declarations: [TypeDemandeurComponent, TypeDemandeurDetailComponent, TypeDemandeurUpdateComponent, TypeDemandeurDeleteDialogComponent],
  entryComponents: [TypeDemandeurDeleteDialogComponent],
})
export class GestionFormationTypeDemandeurModule {}
