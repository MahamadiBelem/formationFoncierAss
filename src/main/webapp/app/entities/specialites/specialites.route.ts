import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISpecialites, Specialites } from 'app/shared/model/specialites.model';
import { SpecialitesService } from './specialites.service';
import { SpecialitesComponent } from './specialites.component';
import { SpecialitesDetailComponent } from './specialites-detail.component';
import { SpecialitesUpdateComponent } from './specialites-update.component';

@Injectable({ providedIn: 'root' })
export class SpecialitesResolve implements Resolve<ISpecialites> {
  constructor(private service: SpecialitesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISpecialites> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((specialites: HttpResponse<Specialites>) => {
          if (specialites.body) {
            return of(specialites.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Specialites());
  }
}

export const specialitesRoute: Routes = [
  {
    path: '',
    component: SpecialitesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.specialites.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SpecialitesDetailComponent,
    resolve: {
      specialites: SpecialitesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.specialites.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SpecialitesUpdateComponent,
    resolve: {
      specialites: SpecialitesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.specialites.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SpecialitesUpdateComponent,
    resolve: {
      specialites: SpecialitesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.specialites.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
