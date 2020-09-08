import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionFormationSharedModule } from 'app/shared/shared.module';
import { SortiePromotionComponent } from './sortie-promotion.component';
import { SortiePromotionDetailComponent } from './sortie-promotion-detail.component';
import { SortiePromotionUpdateComponent } from './sortie-promotion-update.component';
import { SortiePromotionDeleteDialogComponent } from './sortie-promotion-delete-dialog.component';
import { sortiePromotionRoute } from './sortie-promotion.route';

@NgModule({
  imports: [GestionFormationSharedModule, RouterModule.forChild(sortiePromotionRoute)],
  declarations: [
    SortiePromotionComponent,
    SortiePromotionDetailComponent,
    SortiePromotionUpdateComponent,
    SortiePromotionDeleteDialogComponent,
  ],
  entryComponents: [SortiePromotionDeleteDialogComponent],
})
export class GestionFormationSortiePromotionModule {}
