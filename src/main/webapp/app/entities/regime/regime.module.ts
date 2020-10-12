import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { RegimeComponent } from './regime.component';
import { RegimeDetailComponent } from './regime-detail.component';
import { RegimeUpdateComponent } from './regime-update.component';
import { RegimeDeleteDialogComponent } from './regime-delete-dialog.component';
import { regimeRoute } from './regime.route';
import { SaveRegimeComponent } from './save-regime/save-regime.component';
import { UpdateRegimeComponent } from './update-regime/update-regime.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(regimeRoute)],
  declarations: [
    RegimeComponent,
    RegimeDetailComponent,
    RegimeUpdateComponent,
    RegimeDeleteDialogComponent,
    SaveRegimeComponent,
    UpdateRegimeComponent,
  ],
  entryComponents: [RegimeDeleteDialogComponent],
})
export class GestionFormationRegimeModule {}
