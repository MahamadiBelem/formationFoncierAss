import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICaracteristiqueSfr, CaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';
import { CaracteristiqueSfrService } from './caracteristique-sfr.service';
import { CaracteristiqueSfrComponent } from './caracteristique-sfr.component';
import { CaracteristiqueSfrDetailComponent } from './caracteristique-sfr-detail.component';
import { CaracteristiqueSfrUpdateComponent } from './caracteristique-sfr-update.component';

@Injectable({ providedIn: 'root' })
export class CaracteristiqueSfrResolve implements Resolve<ICaracteristiqueSfr> {
  constructor(private service: CaracteristiqueSfrService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICaracteristiqueSfr> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((caracteristiqueSfr: HttpResponse<CaracteristiqueSfr>) => {
          if (caracteristiqueSfr.body) {
            return of(caracteristiqueSfr.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CaracteristiqueSfr());
  }
}

export const caracteristiqueSfrRoute: Routes = [
  {
    path: '',
    component: CaracteristiqueSfrComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.caracteristiqueSfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CaracteristiqueSfrDetailComponent,
    resolve: {
      caracteristiqueSfr: CaracteristiqueSfrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.caracteristiqueSfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CaracteristiqueSfrUpdateComponent,
    resolve: {
      caracteristiqueSfr: CaracteristiqueSfrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.caracteristiqueSfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CaracteristiqueSfrUpdateComponent,
    resolve: {
      caracteristiqueSfr: CaracteristiqueSfrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.caracteristiqueSfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
