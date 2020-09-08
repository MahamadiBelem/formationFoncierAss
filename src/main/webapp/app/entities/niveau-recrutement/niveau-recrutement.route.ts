import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INiveauRecrutement, NiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { NiveauRecrutementService } from './niveau-recrutement.service';
import { NiveauRecrutementComponent } from './niveau-recrutement.component';
import { NiveauRecrutementDetailComponent } from './niveau-recrutement-detail.component';
import { NiveauRecrutementUpdateComponent } from './niveau-recrutement-update.component';

@Injectable({ providedIn: 'root' })
export class NiveauRecrutementResolve implements Resolve<INiveauRecrutement> {
  constructor(private service: NiveauRecrutementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INiveauRecrutement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((niveauRecrutement: HttpResponse<NiveauRecrutement>) => {
          if (niveauRecrutement.body) {
            return of(niveauRecrutement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NiveauRecrutement());
  }
}

export const niveauRecrutementRoute: Routes = [
  {
    path: '',
    component: NiveauRecrutementComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauRecrutement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NiveauRecrutementDetailComponent,
    resolve: {
      niveauRecrutement: NiveauRecrutementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauRecrutement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NiveauRecrutementUpdateComponent,
    resolve: {
      niveauRecrutement: NiveauRecrutementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauRecrutement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NiveauRecrutementUpdateComponent,
    resolve: {
      niveauRecrutement: NiveauRecrutementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauRecrutement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
