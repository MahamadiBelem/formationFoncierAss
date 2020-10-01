import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypecandidatComponent } from './typecandidat.component';
import { TypecandidatDetailComponent } from './typecandidat-detail.component';
import { TypecandidatUpdateComponent } from './typecandidat-update.component';
import { TypecandidatDeleteDialogComponent } from './typecandidat-delete-dialog.component';
import { typecandidatRoute } from './typecandidat.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typecandidatRoute)],
  declarations: [TypecandidatComponent, TypecandidatDetailComponent, TypecandidatUpdateComponent, TypecandidatDeleteDialogComponent],
  entryComponents: [TypecandidatDeleteDialogComponent],
})
export class GestionFormationTypecandidatModule {}
