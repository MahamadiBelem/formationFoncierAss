import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { NiveauInstructionComponent } from './niveau-instruction.component';
import { NiveauInstructionDetailComponent } from './niveau-instruction-detail.component';
import { NiveauInstructionUpdateComponent } from './niveau-instruction-update.component';
import { NiveauInstructionDeleteDialogComponent } from './niveau-instruction-delete-dialog.component';
import { niveauInstructionRoute } from './niveau-instruction.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(niveauInstructionRoute)],
  declarations: [
    NiveauInstructionComponent,
    NiveauInstructionDetailComponent,
    NiveauInstructionUpdateComponent,
    NiveauInstructionDeleteDialogComponent,
  ],
  entryComponents: [NiveauInstructionDeleteDialogComponent],
})
export class GestionFormationNiveauInstructionModule {}
