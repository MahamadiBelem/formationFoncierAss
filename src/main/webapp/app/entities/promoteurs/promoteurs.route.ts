import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPromoteurs, Promoteurs } from 'app/shared/model/promoteurs.model';
import { PromoteursService } from './promoteurs.service';
import { PromoteursComponent } from './promoteurs.component';
import { PromoteursDetailComponent } from './promoteurs-detail.component';
import { PromoteursUpdateComponent } from './promoteurs-update.component';

@Injectable({ providedIn: 'root' })
export class PromoteursResolve implements Resolve<IPromoteurs> {
  constructor(private service: PromoteursService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPromoteurs> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((promoteurs: HttpResponse<Promoteurs>) => {
          if (promoteurs.body) {
            return of(promoteurs.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Promoteurs());
  }
}

export const promoteursRoute: Routes = [
  {
    path: '',
    component: PromoteursComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.promoteurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PromoteursDetailComponent,
    resolve: {
      promoteurs: PromoteursResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.promoteurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PromoteursUpdateComponent,
    resolve: {
      promoteurs: PromoteursResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.promoteurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PromoteursUpdateComponent,
    resolve: {
      promoteurs: PromoteursResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.promoteurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
