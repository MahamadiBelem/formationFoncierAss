import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProvinces, Provinces } from 'app/shared/model/provinces.model';
import { ProvincesService } from './provinces.service';
import { ProvincesComponent } from './provinces.component';
import { ProvincesDetailComponent } from './provinces-detail.component';
import { ProvincesUpdateComponent } from './provinces-update.component';

@Injectable({ providedIn: 'root' })
export class ProvincesResolve implements Resolve<IProvinces> {
  constructor(private service: ProvincesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProvinces> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((provinces: HttpResponse<Provinces>) => {
          if (provinces.body) {
            return of(provinces.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Provinces());
  }
}

export const provincesRoute: Routes = [
  {
    path: '',
    component: ProvincesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.provinces.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProvincesDetailComponent,
    resolve: {
      provinces: ProvincesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.provinces.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProvincesUpdateComponent,
    resolve: {
      provinces: ProvincesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.provinces.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProvincesUpdateComponent,
    resolve: {
      provinces: ProvincesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.provinces.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
