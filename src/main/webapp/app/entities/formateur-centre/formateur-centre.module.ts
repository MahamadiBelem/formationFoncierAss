import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { FormateurCentreComponent } from './formateur-centre.component';
import { FormateurCentreDetailComponent } from './formateur-centre-detail.component';
import { FormateurCentreUpdateComponent } from './formateur-centre-update.component';
import { FormateurCentreDeleteDialogComponent } from './formateur-centre-delete-dialog.component';
import { formateurCentreRoute } from './formateur-centre.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(formateurCentreRoute)],
  declarations: [
    FormateurCentreComponent,
    FormateurCentreDetailComponent,
    FormateurCentreUpdateComponent,
    FormateurCentreDeleteDialogComponent,
  ],
  entryComponents: [FormateurCentreDeleteDialogComponent],
})
export class GestionFormationFormateurCentreModule {}
