import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { TypeInfratructureComponent } from './type-infratructure.component';
import { TypeInfratructureDetailComponent } from './type-infratructure-detail.component';
import { TypeInfratructureUpdateComponent } from './type-infratructure-update.component';
import { TypeInfratructureDeleteDialogComponent } from './type-infratructure-delete-dialog.component';
import { typeInfratructureRoute } from './type-infratructure.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(typeInfratructureRoute)],
  declarations: [
    TypeInfratructureComponent,
    TypeInfratructureDetailComponent,
    TypeInfratructureUpdateComponent,
    TypeInfratructureDeleteDialogComponent,
  ],
  entryComponents: [TypeInfratructureDeleteDialogComponent],
})
export class GestionFormationTypeInfratructureModule {}
