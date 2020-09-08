import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormations, Formations } from 'app/shared/model/formations.model';
import { FormationsService } from './formations.service';
import { FormationsComponent } from './formations.component';
import { FormationsDetailComponent } from './formations-detail.component';
import { FormationsUpdateComponent } from './formations-update.component';

@Injectable({ providedIn: 'root' })
export class FormationsResolve implements Resolve<IFormations> {
  constructor(private service: FormationsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormations> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formations: HttpResponse<Formations>) => {
          if (formations.body) {
            return of(formations.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Formations());
  }
}

export const formationsRoute: Routes = [
  {
    path: '',
    component: FormationsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.formations.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormationsDetailComponent,
    resolve: {
      formations: FormationsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formations.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormationsUpdateComponent,
    resolve: {
      formations: FormationsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formations.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormationsUpdateComponent,
    resolve: {
      formations: FormationsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formations.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
