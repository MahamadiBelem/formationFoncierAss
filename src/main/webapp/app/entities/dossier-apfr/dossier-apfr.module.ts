import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { DossierApfrComponent } from './dossier-apfr.component';
import { DossierApfrDetailComponent } from './dossier-apfr-detail.component';
import { DossierApfrUpdateComponent } from './dossier-apfr-update.component';
import { DossierApfrDeleteDialogComponent } from './dossier-apfr-delete-dialog.component';
import { dossierApfrRoute } from './dossier-apfr.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(dossierApfrRoute)],
  declarations: [DossierApfrComponent, DossierApfrDetailComponent, DossierApfrUpdateComponent, DossierApfrDeleteDialogComponent],
  entryComponents: [DossierApfrDeleteDialogComponent],
})
export class GestionFormationDossierApfrModule {}
