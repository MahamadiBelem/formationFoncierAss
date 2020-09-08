import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeFormation, TypeFormation } from 'app/shared/model/type-formation.model';
import { TypeFormationService } from './type-formation.service';
import { TypeFormationComponent } from './type-formation.component';
import { TypeFormationDetailComponent } from './type-formation-detail.component';
import { TypeFormationUpdateComponent } from './type-formation-update.component';

@Injectable({ providedIn: 'root' })
export class TypeFormationResolve implements Resolve<ITypeFormation> {
  constructor(private service: TypeFormationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeFormation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeFormation: HttpResponse<TypeFormation>) => {
          if (typeFormation.body) {
            return of(typeFormation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeFormation());
  }
}

export const typeFormationRoute: Routes = [
  {
    path: '',
    component: TypeFormationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeFormationDetailComponent,
    resolve: {
      typeFormation: TypeFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeFormationUpdateComponent,
    resolve: {
      typeFormation: TypeFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeFormationUpdateComponent,
    resolve: {
      typeFormation: TypeFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
