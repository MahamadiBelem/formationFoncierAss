import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IKits, Kits } from 'app/shared/model/kits.model';
import { KitsService } from './kits.service';
import { KitsComponent } from './kits.component';
import { KitsDetailComponent } from './kits-detail.component';
import { KitsUpdateComponent } from './kits-update.component';

@Injectable({ providedIn: 'root' })
export class KitsResolve implements Resolve<IKits> {
  constructor(private service: KitsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IKits> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((kits: HttpResponse<Kits>) => {
          if (kits.body) {
            return of(kits.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Kits());
  }
}

export const kitsRoute: Routes = [
  {
    path: '',
    component: KitsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.kits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: KitsDetailComponent,
    resolve: {
      kits: KitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.kits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: KitsUpdateComponent,
    resolve: {
      kits: KitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.kits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: KitsUpdateComponent,
    resolve: {
      kits: KitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.kits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
