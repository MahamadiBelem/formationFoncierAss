import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { PromoteursComponent } from './promoteurs.component';
import { PromoteursDetailComponent } from './promoteurs-detail.component';
import { PromoteursUpdateComponent } from './promoteurs-update.component';
import { PromoteursDeleteDialogComponent } from './promoteurs-delete-dialog.component';
import { promoteursRoute } from './promoteurs.route';
import { SavePromoteurComponent } from './save-promoteur/save-promoteur.component';
import { UpdatePromoteurComponent } from './update-promoteur/update-promoteur.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(promoteursRoute)],
  declarations: [
    PromoteursComponent,
    PromoteursDetailComponent,
    PromoteursUpdateComponent,
    PromoteursDeleteDialogComponent,
    SavePromoteurComponent,
    UpdatePromoteurComponent,
  ],
  entryComponents: [PromoteursDeleteDialogComponent],
})
export class GestionFormationPromoteursModule {}
