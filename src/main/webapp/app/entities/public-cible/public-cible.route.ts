import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPublicCible, PublicCible } from 'app/shared/model/public-cible.model';
import { PublicCibleService } from './public-cible.service';
import { PublicCibleComponent } from './public-cible.component';
import { PublicCibleDetailComponent } from './public-cible-detail.component';
import { PublicCibleUpdateComponent } from './public-cible-update.component';

@Injectable({ providedIn: 'root' })
export class PublicCibleResolve implements Resolve<IPublicCible> {
  constructor(private service: PublicCibleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPublicCible> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((publicCible: HttpResponse<PublicCible>) => {
          if (publicCible.body) {
            return of(publicCible.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PublicCible());
  }
}

export const publicCibleRoute: Routes = [
  {
    path: '',
    component: PublicCibleComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.publicCible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PublicCibleDetailComponent,
    resolve: {
      publicCible: PublicCibleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.publicCible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PublicCibleUpdateComponent,
    resolve: {
      publicCible: PublicCibleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.publicCible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PublicCibleUpdateComponent,
    resolve: {
      publicCible: PublicCibleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.publicCible.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
