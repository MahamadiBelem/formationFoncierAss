import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeActe, TypeActe } from 'app/shared/model/type-acte.model';
import { TypeActeService } from './type-acte.service';
import { TypeActeComponent } from './type-acte.component';
import { TypeActeDetailComponent } from './type-acte-detail.component';
import { TypeActeUpdateComponent } from './type-acte-update.component';

@Injectable({ providedIn: 'root' })
export class TypeActeResolve implements Resolve<ITypeActe> {
  constructor(private service: TypeActeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeActe> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeActe: HttpResponse<TypeActe>) => {
          if (typeActe.body) {
            return of(typeActe.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeActe());
  }
}

export const typeActeRoute: Routes = [
  {
    path: '',
    component: TypeActeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeActe.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeActeDetailComponent,
    resolve: {
      typeActe: TypeActeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeActe.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeActeUpdateComponent,
    resolve: {
      typeActe: TypeActeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeActe.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeActeUpdateComponent,
    resolve: {
      typeActe: TypeActeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typeActe.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
