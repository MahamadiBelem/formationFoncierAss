import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVillages, Villages } from 'app/shared/model/villages.model';
import { VillagesService } from './villages.service';
import { VillagesComponent } from './villages.component';
import { VillagesDetailComponent } from './villages-detail.component';
import { VillagesUpdateComponent } from './villages-update.component';

@Injectable({ providedIn: 'root' })
export class VillagesResolve implements Resolve<IVillages> {
  constructor(private service: VillagesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVillages> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((villages: HttpResponse<Villages>) => {
          if (villages.body) {
            return of(villages.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Villages());
  }
}

export const villagesRoute: Routes = [
  {
    path: '',
    component: VillagesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.villages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VillagesDetailComponent,
    resolve: {
      villages: VillagesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.villages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VillagesUpdateComponent,
    resolve: {
      villages: VillagesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.villages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VillagesUpdateComponent,
    resolve: {
      villages: VillagesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.villages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
