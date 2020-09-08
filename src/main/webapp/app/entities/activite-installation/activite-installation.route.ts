import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IActiviteInstallation, ActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from './activite-installation.service';
import { ActiviteInstallationComponent } from './activite-installation.component';
import { ActiviteInstallationDetailComponent } from './activite-installation-detail.component';
import { ActiviteInstallationUpdateComponent } from './activite-installation-update.component';

@Injectable({ providedIn: 'root' })
export class ActiviteInstallationResolve implements Resolve<IActiviteInstallation> {
  constructor(private service: ActiviteInstallationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IActiviteInstallation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((activiteInstallation: HttpResponse<ActiviteInstallation>) => {
          if (activiteInstallation.body) {
            return of(activiteInstallation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ActiviteInstallation());
  }
}

export const activiteInstallationRoute: Routes = [
  {
    path: '',
    component: ActiviteInstallationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.activiteInstallation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ActiviteInstallationDetailComponent,
    resolve: {
      activiteInstallation: ActiviteInstallationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.activiteInstallation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ActiviteInstallationUpdateComponent,
    resolve: {
      activiteInstallation: ActiviteInstallationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.activiteInstallation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ActiviteInstallationUpdateComponent,
    resolve: {
      activiteInstallation: ActiviteInstallationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.activiteInstallation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
