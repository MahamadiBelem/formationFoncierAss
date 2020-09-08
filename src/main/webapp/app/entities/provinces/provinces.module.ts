import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ProvincesComponent } from './provinces.component';
import { ProvincesDetailComponent } from './provinces-detail.component';
import { ProvincesUpdateComponent } from './provinces-update.component';
import { ProvincesDeleteDialogComponent } from './provinces-delete-dialog.component';
import { provincesRoute } from './provinces.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(provincesRoute)],
  declarations: [ProvincesComponent, ProvincesDetailComponent, ProvincesUpdateComponent, ProvincesDeleteDialogComponent],
  entryComponents: [ProvincesDeleteDialogComponent],
})
export class GestionFormationProvincesModule {}
