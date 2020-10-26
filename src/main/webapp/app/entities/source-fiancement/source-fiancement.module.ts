import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { SourceFiancementComponent } from './source-fiancement.component';
import { SourceFiancementDetailComponent } from './source-fiancement-detail.component';
import { SourceFiancementUpdateComponent } from './source-fiancement-update.component';
import { SourceFiancementDeleteDialogComponent } from './source-fiancement-delete-dialog.component';
import { sourceFiancementRoute } from './source-fiancement.route';
import { SaveSourceFinancementComponent } from './save-source-financement/save-source-financement.component';
import { UpdateSourceFinancementComponent } from './update-source-financement/update-source-financement.component';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(sourceFiancementRoute)],
  declarations: [
    SourceFiancementComponent,
    SourceFiancementDetailComponent,
    SourceFiancementUpdateComponent,
    SourceFiancementDeleteDialogComponent,
    SaveSourceFinancementComponent,
    UpdateSourceFinancementComponent,
  ],
  entryComponents: [SourceFiancementDeleteDialogComponent],
})
export class GestionFormationSourceFiancementModule {}
