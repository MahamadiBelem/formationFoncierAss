import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormationSFRComponent } from './formation-sfr.component';
import { FormationSFRDetailComponent } from './formation-sfr-detail.component';
import { FormationSFRUpdateComponent } from './formation-sfr-update.component';
import { FormationSFRDeleteDialogComponent } from './formation-sfr-delete-dialog.component';
import { formationSFRRoute } from './formation-sfr.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formationSFRRoute)],
  declarations: [FormationSFRComponent, FormationSFRDetailComponent, FormationSFRUpdateComponent, FormationSFRDeleteDialogComponent],
  entryComponents: [FormationSFRDeleteDialogComponent],
})
export class GestionFormationFormationSFRModule {}
