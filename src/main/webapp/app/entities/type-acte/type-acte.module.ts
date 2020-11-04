import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypeActeComponent } from './type-acte.component';
import { TypeActeDetailComponent } from './type-acte-detail.component';
import { TypeActeUpdateComponent } from './type-acte-update.component';
import { TypeActeDeleteDialogComponent } from './type-acte-delete-dialog.component';
import { typeActeRoute } from './type-acte.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typeActeRoute)],
  declarations: [TypeActeComponent, TypeActeDetailComponent, TypeActeUpdateComponent, TypeActeDeleteDialogComponent],
  entryComponents: [TypeActeDeleteDialogComponent],
})
export class GestionFormationTypeActeModule {}
