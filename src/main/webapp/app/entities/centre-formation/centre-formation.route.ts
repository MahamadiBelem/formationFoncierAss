import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICentreFormation, CentreFormation } from 'app/shared/model/centre-formation.model';
import { CentreFormationService } from './centre-formation.service';
import { CentreFormationComponent } from './centre-formation.component';
import { CentreFormationDetailComponent } from './centre-formation-detail.component';
import { CentreFormationUpdateComponent } from './centre-formation-update.component';

@Injectable({ providedIn: 'root' })
export class CentreFormationResolve implements Resolve<ICentreFormation> {
  constructor(private service: CentreFormationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICentreFormation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((centreFormation: HttpResponse<CentreFormation>) => {
          if (centreFormation.body) {
            return of(centreFormation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CentreFormation());
  }
}

export const centreFormationRoute: Routes = [
  {
    path: '',
    component: CentreFormationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.centreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CentreFormationDetailComponent,
    resolve: {
      centreFormation: CentreFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.centreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CentreFormationUpdateComponent,
    resolve: {
      centreFormation: CentreFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.centreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CentreFormationUpdateComponent,
    resolve: {
      centreFormation: CentreFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.centreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
