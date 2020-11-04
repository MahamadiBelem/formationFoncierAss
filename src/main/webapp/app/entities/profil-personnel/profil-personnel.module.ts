import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ProfilPersonnelComponent } from './profil-personnel.component';
import { ProfilPersonnelDetailComponent } from './profil-personnel-detail.component';
import { ProfilPersonnelUpdateComponent } from './profil-personnel-update.component';
import { ProfilPersonnelDeleteDialogComponent } from './profil-personnel-delete-dialog.component';
import { profilPersonnelRoute } from './profil-personnel.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(profilPersonnelRoute)],
  declarations: [
    ProfilPersonnelComponent,
    ProfilPersonnelDetailComponent,
    ProfilPersonnelUpdateComponent,
    ProfilPersonnelDeleteDialogComponent,
  ],
  entryComponents: [ProfilPersonnelDeleteDialogComponent],
})
export class GestionFormationProfilPersonnelModule {}
