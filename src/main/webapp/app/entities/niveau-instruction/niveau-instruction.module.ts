import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { NiveauInstructionComponent } from './niveau-instruction.component';
import { NiveauInstructionDetailComponent } from './niveau-instruction-detail.component';
import { NiveauInstructionUpdateComponent } from './niveau-instruction-update.component';
import { NiveauInstructionDeleteDialogComponent } from './niveau-instruction-delete-dialog.component';
import { niveauInstructionRoute } from './niveau-instruction.route';
import { SaveNiveauComponent } from './save-niveau/save-niveau.component';
import { UpdateNiveauComponent } from './update-niveau/update-niveau.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(niveauInstructionRoute)],
  declarations: [
    NiveauInstructionComponent,
    NiveauInstructionDetailComponent,
    NiveauInstructionUpdateComponent,
    NiveauInstructionDeleteDialogComponent,
    SaveNiveauComponent,
    UpdateNiveauComponent,
  ],
  entryComponents: [NiveauInstructionDeleteDialogComponent],
})
export class GestionFormationNiveauInstructionModule {}
