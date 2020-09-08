import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormationsComponent } from './formations.component';
import { FormationsDetailComponent } from './formations-detail.component';
import { FormationsUpdateComponent } from './formations-update.component';
import { FormationsDeleteDialogComponent } from './formations-delete-dialog.component';
import { formationsRoute } from './formations.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formationsRoute)],
  declarations: [FormationsComponent, FormationsDetailComponent, FormationsUpdateComponent, FormationsDeleteDialogComponent],
  entryComponents: [FormationsDeleteDialogComponent],
})
export class GestionFormationFormationsModule {}
