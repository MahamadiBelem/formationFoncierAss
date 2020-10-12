import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ProvincesComponent } from './provinces.component';
import { ProvincesDetailComponent } from './provinces-detail.component';
import { ProvincesUpdateComponent } from './provinces-update.component';
import { ProvincesDeleteDialogComponent } from './provinces-delete-dialog.component';
import { provincesRoute } from './provinces.route';
import { SaveProvinceComponent } from './save-province/save-province.component';
import { UpdateProvincesComponent } from './update-provinces/update-provinces.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(provincesRoute)],
  declarations: [
    ProvincesComponent,
    ProvincesDetailComponent,
    ProvincesUpdateComponent,
    ProvincesDeleteDialogComponent,
    SaveProvinceComponent,
    UpdateProvincesComponent,
  ],
  entryComponents: [ProvincesDeleteDialogComponent],
})
export class GestionFormationProvincesModule {}
