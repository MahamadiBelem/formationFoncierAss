import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommunes, Communes } from 'app/shared/model/communes.model';
import { CommunesService } from './communes.service';
import { CommunesComponent } from './communes.component';
import { CommunesDetailComponent } from './communes-detail.component';
import { CommunesUpdateComponent } from './communes-update.component';

@Injectable({ providedIn: 'root' })
export class CommunesResolve implements Resolve<ICommunes> {
  constructor(private service: CommunesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommunes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((communes: HttpResponse<Communes>) => {
          if (communes.body) {
            return of(communes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Communes());
  }
}

export const communesRoute: Routes = [
  {
    path: '',
    component: CommunesComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.communes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommunesDetailComponent,
    resolve: {
      communes: CommunesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.communes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommunesUpdateComponent,
    resolve: {
      communes: CommunesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.communes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommunesUpdateComponent,
    resolve: {
      communes: CommunesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.communes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
