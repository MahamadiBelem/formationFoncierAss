import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormateursComponent } from './formateurs.component';
import { FormateursDetailComponent } from './formateurs-detail.component';
import { FormateursUpdateComponent } from './formateurs-update.component';
import { FormateursDeleteDialogComponent } from './formateurs-delete-dialog.component';
import { formateursRoute } from './formateurs.route';
import { SaveFormateurComponent } from './save-formateur/save-formateur.component';
import { UpdateFormateurComponent } from './update-formateur/update-formateur.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formateursRoute)],
  declarations: [
    FormateursComponent,
    FormateursDetailComponent,
    FormateursUpdateComponent,
    FormateursDeleteDialogComponent,
    SaveFormateurComponent,
    UpdateFormateurComponent,
  ],
  entryComponents: [FormateursDeleteDialogComponent],
})
export class GestionFormationFormateursModule {}
