import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormateursComponent } from './formateurs.component';
import { FormateursDetailComponent } from './formateurs-detail.component';
import { FormateursUpdateComponent } from './formateurs-update.component';
import { FormateursDeleteDialogComponent } from './formateurs-delete-dialog.component';
import { formateursRoute } from './formateurs.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formateursRoute)],
  declarations: [FormateursComponent, FormateursDetailComponent, FormateursUpdateComponent, FormateursDeleteDialogComponent],
  entryComponents: [FormateursDeleteDialogComponent],
})
export class GestionFormationFormateursModule {}
