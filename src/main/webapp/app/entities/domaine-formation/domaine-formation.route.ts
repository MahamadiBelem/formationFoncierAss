import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDomaineFormation, DomaineFormation } from 'app/shared/model/domaine-formation.model';
import { DomaineFormationService } from './domaine-formation.service';
import { DomaineFormationComponent } from './domaine-formation.component';
import { DomaineFormationDetailComponent } from './domaine-formation-detail.component';
import { DomaineFormationUpdateComponent } from './domaine-formation-update.component';

@Injectable({ providedIn: 'root' })
export class DomaineFormationResolve implements Resolve<IDomaineFormation> {
  constructor(private service: DomaineFormationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDomaineFormation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((domaineFormation: HttpResponse<DomaineFormation>) => {
          if (domaineFormation.body) {
            return of(domaineFormation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DomaineFormation());
  }
}

export const domaineFormationRoute: Routes = [
  {
    path: '',
    component: DomaineFormationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.domaineFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DomaineFormationDetailComponent,
    resolve: {
      domaineFormation: DomaineFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.domaineFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DomaineFormationUpdateComponent,
    resolve: {
      domaineFormation: DomaineFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.domaineFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DomaineFormationUpdateComponent,
    resolve: {
      domaineFormation: DomaineFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.domaineFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
