import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ApprochePedagogiqueComponent } from './approche-pedagogique.component';
import { ApprochePedagogiqueDetailComponent } from './approche-pedagogique-detail.component';
import { ApprochePedagogiqueUpdateComponent } from './approche-pedagogique-update.component';
import { ApprochePedagogiqueDeleteDialogComponent } from './approche-pedagogique-delete-dialog.component';
import { approchePedagogiqueRoute } from './approche-pedagogique.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(approchePedagogiqueRoute)],
  declarations: [
    ApprochePedagogiqueComponent,
    ApprochePedagogiqueDetailComponent,
    ApprochePedagogiqueUpdateComponent,
    ApprochePedagogiqueDeleteDialogComponent,
  ],
  entryComponents: [ApprochePedagogiqueDeleteDialogComponent],
})
export class GestionFormationApprochePedagogiqueModule {}
