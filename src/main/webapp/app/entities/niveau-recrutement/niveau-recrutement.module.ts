import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { NiveauRecrutementComponent } from './niveau-recrutement.component';
import { NiveauRecrutementDetailComponent } from './niveau-recrutement-detail.component';
import { NiveauRecrutementUpdateComponent } from './niveau-recrutement-update.component';
import { NiveauRecrutementDeleteDialogComponent } from './niveau-recrutement-delete-dialog.component';
import { niveauRecrutementRoute } from './niveau-recrutement.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(niveauRecrutementRoute)],
  declarations: [
    NiveauRecrutementComponent,
    NiveauRecrutementDetailComponent,
    NiveauRecrutementUpdateComponent,
    NiveauRecrutementDeleteDialogComponent,
  ],
  entryComponents: [NiveauRecrutementDeleteDialogComponent],
})
export class GestionFormationNiveauRecrutementModule {}
