import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISortiePromotion, SortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { SortiePromotionService } from './sortie-promotion.service';
import { SortiePromotionComponent } from './sortie-promotion.component';
import { SortiePromotionDetailComponent } from './sortie-promotion-detail.component';
import { SortiePromotionUpdateComponent } from './sortie-promotion-update.component';

@Injectable({ providedIn: 'root' })
export class SortiePromotionResolve implements Resolve<ISortiePromotion> {
  constructor(private service: SortiePromotionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISortiePromotion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sortiePromotion: HttpResponse<SortiePromotion>) => {
          if (sortiePromotion.body) {
            return of(sortiePromotion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SortiePromotion());
  }
}

export const sortiePromotionRoute: Routes = [
  {
    path: '',
    component: SortiePromotionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.sortiePromotion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SortiePromotionDetailComponent,
    resolve: {
      sortiePromotion: SortiePromotionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sortiePromotion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SortiePromotionUpdateComponent,
    resolve: {
      sortiePromotion: SortiePromotionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sortiePromotion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SortiePromotionUpdateComponent,
    resolve: {
      sortiePromotion: SortiePromotionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sortiePromotion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
