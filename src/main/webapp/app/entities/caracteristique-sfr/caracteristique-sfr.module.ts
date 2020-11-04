import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { CaracteristiqueSfrComponent } from './caracteristique-sfr.component';
import { CaracteristiqueSfrDetailComponent } from './caracteristique-sfr-detail.component';
import { CaracteristiqueSfrUpdateComponent } from './caracteristique-sfr-update.component';
import { CaracteristiqueSfrDeleteDialogComponent } from './caracteristique-sfr-delete-dialog.component';
import { caracteristiqueSfrRoute } from './caracteristique-sfr.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(caracteristiqueSfrRoute)],
  declarations: [
    CaracteristiqueSfrComponent,
    CaracteristiqueSfrDetailComponent,
    CaracteristiqueSfrUpdateComponent,
    CaracteristiqueSfrDeleteDialogComponent,
  ],
  entryComponents: [CaracteristiqueSfrDeleteDialogComponent],
})
export class GestionFormationCaracteristiqueSfrModule {}
