import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { SfrComponent } from './sfr.component';
import { SfrDetailComponent } from './sfr-detail.component';
import { SfrUpdateComponent } from './sfr-update.component';
import { SfrDeleteDialogComponent } from './sfr-delete-dialog.component';
import { sfrRoute } from './sfr.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(sfrRoute)],
  declarations: [SfrComponent, SfrDetailComponent, SfrUpdateComponent, SfrDeleteDialogComponent],
  entryComponents: [SfrDeleteDialogComponent],
})
export class GestionFormationSfrModule {}
