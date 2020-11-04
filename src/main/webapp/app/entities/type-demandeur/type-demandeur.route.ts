import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeDemandeur, TypeDemandeur } from 'app/shared/model/type-demandeur.model';
import { TypeDemandeurService } from './type-demandeur.service';
import { TypeDemandeurComponent } from './type-demandeur.component';
import { TypeDemandeurDetailComponent } from './type-demandeur-detail.component';
import { TypeDemandeurUpdateComponent } from './type-demandeur-update.component';

@Injectable({ providedIn: 'root' })
export class TypeDemandeurResolve implements Resolve<ITypeDemandeur> {
  constructor(private service: TypeDemandeurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeDemandeur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeDemandeur: HttpResponse<TypeDemandeur>) => {
          if (typeDemandeur.body) {
            return of(typeDemandeur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeDemandeur());
  }
}

export const typeDemandeurRoute: Routes = [
  {
    path: '',
    component: TypeDemandeurComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeDemandeur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeDemandeurDetailComponent,
    resolve: {
      typeDemandeur: TypeDemandeurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeDemandeur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeDemandeurUpdateComponent,
    resolve: {
      typeDemandeur: TypeDemandeurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeDemandeur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeDemandeurUpdateComponent,
    resolve: {
      typeDemandeur: TypeDemandeurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeDemandeur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
