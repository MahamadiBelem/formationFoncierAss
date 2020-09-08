import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { CompositionKitsComponent } from './composition-kits.component';
import { CompositionKitsDetailComponent } from './composition-kits-detail.component';
import { CompositionKitsUpdateComponent } from './composition-kits-update.component';
import { CompositionKitsDeleteDialogComponent } from './composition-kits-delete-dialog.component';
import { compositionKitsRoute } from './composition-kits.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(compositionKitsRoute)],
  declarations: [
    CompositionKitsComponent,
    CompositionKitsDetailComponent,
    CompositionKitsUpdateComponent,
    CompositionKitsDeleteDialogComponent,
  ],
  entryComponents: [CompositionKitsDeleteDialogComponent],
})
export class GestionFormationCompositionKitsModule {}
