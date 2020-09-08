import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ConditionAccessComponent } from './condition-access.component';
import { ConditionAccessDetailComponent } from './condition-access-detail.component';
import { ConditionAccessUpdateComponent } from './condition-access-update.component';
import { ConditionAccessDeleteDialogComponent } from './condition-access-delete-dialog.component';
import { conditionAccessRoute } from './condition-access.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(conditionAccessRoute)],
  declarations: [
    ConditionAccessComponent,
    ConditionAccessDetailComponent,
    ConditionAccessUpdateComponent,
    ConditionAccessDeleteDialogComponent,
  ],
  entryComponents: [ConditionAccessDeleteDialogComponent],
})
export class GestionFormationConditionAccessModule {}
