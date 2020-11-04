import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IImmaDomaine, ImmaDomaine } from 'app/shared/model/imma-domaine.model';
import { ImmaDomaineService } from './imma-domaine.service';
import { ImmaDomaineComponent } from './imma-domaine.component';
import { ImmaDomaineDetailComponent } from './imma-domaine-detail.component';
import { ImmaDomaineUpdateComponent } from './imma-domaine-update.component';

@Injectable({ providedIn: 'root' })
export class ImmaDomaineResolve implements Resolve<IImmaDomaine> {
  constructor(private service: ImmaDomaineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IImmaDomaine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((immaDomaine: HttpResponse<ImmaDomaine>) => {
          if (immaDomaine.body) {
            return of(immaDomaine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ImmaDomaine());
  }
}

export const immaDomaineRoute: Routes = [
  {
    path: '',
    component: ImmaDomaineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.immaDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ImmaDomaineDetailComponent,
    resolve: {
      immaDomaine: ImmaDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.immaDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ImmaDomaineUpdateComponent,
    resolve: {
      immaDomaine: ImmaDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.immaDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ImmaDomaineUpdateComponent,
    resolve: {
      immaDomaine: ImmaDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.immaDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
