import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormateurs, Formateurs } from 'app/shared/model/formateurs.model';
import { FormateursService } from './formateurs.service';
import { FormateursComponent } from './formateurs.component';
import { FormateursDetailComponent } from './formateurs-detail.component';
import { FormateursUpdateComponent } from './formateurs-update.component';

@Injectable({ providedIn: 'root' })
export class FormateursResolve implements Resolve<IFormateurs> {
  constructor(private service: FormateursService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormateurs> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formateurs: HttpResponse<Formateurs>) => {
          if (formateurs.body) {
            return of(formateurs.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Formateurs());
  }
}

export const formateursRoute: Routes = [
  {
    path: '',
    component: FormateursComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormateursDetailComponent,
    resolve: {
      formateurs: FormateursResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormateursUpdateComponent,
    resolve: {
      formateurs: FormateursResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormateursUpdateComponent,
    resolve: {
      formateurs: FormateursResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurs.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
