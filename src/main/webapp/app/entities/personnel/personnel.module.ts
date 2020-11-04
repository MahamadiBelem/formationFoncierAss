import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { PersonnelComponent } from './personnel.component';
import { PersonnelDetailComponent } from './personnel-detail.component';
import { PersonnelUpdateComponent } from './personnel-update.component';
import { PersonnelDeleteDialogComponent } from './personnel-delete-dialog.component';
import { personnelRoute } from './personnel.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(personnelRoute)],
  declarations: [PersonnelComponent, PersonnelDetailComponent, PersonnelUpdateComponent, PersonnelDeleteDialogComponent],
  entryComponents: [PersonnelDeleteDialogComponent],
})
export class GestionFormationPersonnelModule {}
