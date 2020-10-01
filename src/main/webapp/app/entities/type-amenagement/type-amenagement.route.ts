import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeAmenagement, TypeAmenagement } from 'app/shared/model/type-amenagement.model';
import { TypeAmenagementService } from './type-amenagement.service';
import { TypeAmenagementComponent } from './type-amenagement.component';
import { TypeAmenagementDetailComponent } from './type-amenagement-detail.component';
import { TypeAmenagementUpdateComponent } from './type-amenagement-update.component';

@Injectable({ providedIn: 'root' })
export class TypeAmenagementResolve implements Resolve<ITypeAmenagement> {
  constructor(private service: TypeAmenagementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeAmenagement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeAmenagement: HttpResponse<TypeAmenagement>) => {
          if (typeAmenagement.body) {
            return of(typeAmenagement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeAmenagement());
  }
}

export const typeAmenagementRoute: Routes = [
  {
    path: '',
    component: TypeAmenagementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.typeAmenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeAmenagementDetailComponent,
    resolve: {
      typeAmenagement: TypeAmenagementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeAmenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeAmenagementUpdateComponent,
    resolve: {
      typeAmenagement: TypeAmenagementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeAmenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeAmenagementUpdateComponent,
    resolve: {
      typeAmenagement: TypeAmenagementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeAmenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
