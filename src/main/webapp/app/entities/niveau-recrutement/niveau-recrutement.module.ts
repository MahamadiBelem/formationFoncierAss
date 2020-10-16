import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { NiveauRecrutementComponent } from './niveau-recrutement.component';
import { NiveauRecrutementDetailComponent } from './niveau-recrutement-detail.component';
import { NiveauRecrutementUpdateComponent } from './niveau-recrutement-update.component';
import { NiveauRecrutementDeleteDialogComponent } from './niveau-recrutement-delete-dialog.component';
import { niveauRecrutementRoute } from './niveau-recrutement.route';
import { SaveNiveauRecrutementComponent } from './save-niveau-recrutement/save-niveau-recrutement.component';
import { UpdateNiveauRecrutementComponent } from './update-niveau-recrutement/update-niveau-recrutement.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(niveauRecrutementRoute)],
  declarations: [
    NiveauRecrutementComponent,
    NiveauRecrutementDetailComponent,
    NiveauRecrutementUpdateComponent,
    NiveauRecrutementDeleteDialogComponent,
    SaveNiveauRecrutementComponent,
    UpdateNiveauRecrutementComponent,
  ],
  entryComponents: [NiveauRecrutementDeleteDialogComponent],
})
export class GestionFormationNiveauRecrutementModule {}
