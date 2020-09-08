import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IConditionAccess, ConditionAccess } from 'app/shared/model/condition-access.model';
import { ConditionAccessService } from './condition-access.service';
import { ConditionAccessComponent } from './condition-access.component';
import { ConditionAccessDetailComponent } from './condition-access-detail.component';
import { ConditionAccessUpdateComponent } from './condition-access-update.component';

@Injectable({ providedIn: 'root' })
export class ConditionAccessResolve implements Resolve<IConditionAccess> {
  constructor(private service: ConditionAccessService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConditionAccess> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((conditionAccess: HttpResponse<ConditionAccess>) => {
          if (conditionAccess.body) {
            return of(conditionAccess.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ConditionAccess());
  }
}

export const conditionAccessRoute: Routes = [
  {
    path: '',
    component: ConditionAccessComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.conditionAccess.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ConditionAccessDetailComponent,
    resolve: {
      conditionAccess: ConditionAccessResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.conditionAccess.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ConditionAccessUpdateComponent,
    resolve: {
      conditionAccess: ConditionAccessResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.conditionAccess.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ConditionAccessUpdateComponent,
    resolve: {
      conditionAccess: ConditionAccessResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.conditionAccess.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
