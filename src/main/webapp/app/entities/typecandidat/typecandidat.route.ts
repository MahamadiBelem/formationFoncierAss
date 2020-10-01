import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypecandidat, Typecandidat } from 'app/shared/model/typecandidat.model';
import { TypecandidatService } from './typecandidat.service';
import { TypecandidatComponent } from './typecandidat.component';
import { TypecandidatDetailComponent } from './typecandidat-detail.component';
import { TypecandidatUpdateComponent } from './typecandidat-update.component';

@Injectable({ providedIn: 'root' })
export class TypecandidatResolve implements Resolve<ITypecandidat> {
  constructor(private service: TypecandidatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypecandidat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typecandidat: HttpResponse<Typecandidat>) => {
          if (typecandidat.body) {
            return of(typecandidat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Typecandidat());
  }
}

export const typecandidatRoute: Routes = [
  {
    path: '',
    component: TypecandidatComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typecandidat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypecandidatDetailComponent,
    resolve: {
      typecandidat: TypecandidatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typecandidat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypecandidatUpdateComponent,
    resolve: {
      typecandidat: TypecandidatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typecandidat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypecandidatUpdateComponent,
    resolve: {
      typecandidat: TypecandidatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.typecandidat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
