import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDossierApfr, DossierApfr } from 'app/shared/model/dossier-apfr.model';
import { DossierApfrService } from './dossier-apfr.service';
import { DossierApfrComponent } from './dossier-apfr.component';
import { DossierApfrDetailComponent } from './dossier-apfr-detail.component';
import { DossierApfrUpdateComponent } from './dossier-apfr-update.component';

@Injectable({ providedIn: 'root' })
export class DossierApfrResolve implements Resolve<IDossierApfr> {
  constructor(private service: DossierApfrService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDossierApfr> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dossierApfr: HttpResponse<DossierApfr>) => {
          if (dossierApfr.body) {
            return of(dossierApfr.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DossierApfr());
  }
}

export const dossierApfrRoute: Routes = [
  {
    path: '',
    component: DossierApfrComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.dossierApfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DossierApfrDetailComponent,
    resolve: {
      dossierApfr: DossierApfrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.dossierApfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DossierApfrUpdateComponent,
    resolve: {
      dossierApfr: DossierApfrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.dossierApfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DossierApfrUpdateComponent,
    resolve: {
      dossierApfr: DossierApfrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.dossierApfr.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
