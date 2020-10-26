import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { CompositionKitsComponent } from './composition-kits.component';
import { CompositionKitsDetailComponent } from './composition-kits-detail.component';
import { CompositionKitsUpdateComponent } from './composition-kits-update.component';
import { CompositionKitsDeleteDialogComponent } from './composition-kits-delete-dialog.component';
import { compositionKitsRoute } from './composition-kits.route';
import { SaveCompositionKitsComponent } from './save-composition-kits/save-composition-kits.component';
import { UpdateCompositionKitsComponent } from './update-composition-kits/update-composition-kits.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(compositionKitsRoute)],
  declarations: [
    CompositionKitsComponent,
    CompositionKitsDetailComponent,
    CompositionKitsUpdateComponent,
    CompositionKitsDeleteDialogComponent,
    SaveCompositionKitsComponent,
    UpdateCompositionKitsComponent,
  ],
  entryComponents: [CompositionKitsDeleteDialogComponent],
})
export class GestionFormationCompositionKitsModule {}
