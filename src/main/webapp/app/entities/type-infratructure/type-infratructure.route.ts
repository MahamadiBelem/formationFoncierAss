import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeInfratructure, TypeInfratructure } from 'app/shared/model/type-infratructure.model';
import { TypeInfratructureService } from './type-infratructure.service';
import { TypeInfratructureComponent } from './type-infratructure.component';
import { TypeInfratructureDetailComponent } from './type-infratructure-detail.component';
import { TypeInfratructureUpdateComponent } from './type-infratructure-update.component';

@Injectable({ providedIn: 'root' })
export class TypeInfratructureResolve implements Resolve<ITypeInfratructure> {
  constructor(private service: TypeInfratructureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeInfratructure> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeInfratructure: HttpResponse<TypeInfratructure>) => {
          if (typeInfratructure.body) {
            return of(typeInfratructure.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeInfratructure());
  }
}

export const typeInfratructureRoute: Routes = [
  {
    path: '',
    component: TypeInfratructureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.typeInfratructure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeInfratructureDetailComponent,
    resolve: {
      typeInfratructure: TypeInfratructureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeInfratructure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeInfratructureUpdateComponent,
    resolve: {
      typeInfratructure: TypeInfratructureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeInfratructure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeInfratructureUpdateComponent,
    resolve: {
      typeInfratructure: TypeInfratructureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeInfratructure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
