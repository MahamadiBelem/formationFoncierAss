import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IApprenantes, Apprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from './apprenantes.service';
import { ApprenantesComponent } from './apprenantes.component';
import { ApprenantesDetailComponent } from './apprenantes-detail.component';
import { ApprenantesUpdateComponent } from './apprenantes-update.component';

@Injectable({ providedIn: 'root' })
export class ApprenantesResolve implements Resolve<IApprenantes> {
  constructor(private service: ApprenantesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IApprenantes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((apprenantes: HttpResponse<Apprenantes>) => {
          if (apprenantes.body) {
            return of(apprenantes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Apprenantes());
  }
}

export const apprenantesRoute: Routes = [
  {
    path: '',
    component: ApprenantesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.apprenantes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApprenantesDetailComponent,
    resolve: {
      apprenantes: ApprenantesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.apprenantes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApprenantesUpdateComponent,
    resolve: {
      apprenantes: ApprenantesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.apprenantes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApprenantesUpdateComponent,
    resolve: {
      apprenantes: ApprenantesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.apprenantes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
