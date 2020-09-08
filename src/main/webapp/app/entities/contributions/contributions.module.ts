import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { ContributionsComponent } from './contributions.component';
import { ContributionsDetailComponent } from './contributions-detail.component';
import { ContributionsUpdateComponent } from './contributions-update.component';
import { ContributionsDeleteDialogComponent } from './contributions-delete-dialog.component';
import { contributionsRoute } from './contributions.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(contributionsRoute)],
  declarations: [ContributionsComponent, ContributionsDetailComponent, ContributionsUpdateComponent, ContributionsDeleteDialogComponent],
  entryComponents: [ContributionsDeleteDialogComponent],
})
export class GestionFormationContributionsModule {}
