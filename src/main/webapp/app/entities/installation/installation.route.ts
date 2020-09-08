import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInstallation, Installation } from 'app/shared/model/installation.model';
import { InstallationService } from './installation.service';
import { InstallationComponent } from './installation.component';
import { InstallationDetailComponent } from './installation-detail.component';
import { InstallationUpdateComponent } from './installation-update.component';

@Injectable({ providedIn: 'root' })
export class InstallationResolve implements Resolve<IInstallation> {
  constructor(private service: InstallationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInstallation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((installation: HttpResponse<Installation>) => {
          if (installation.body) {
            return of(installation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Installation());
  }
}

export const installationRoute: Routes = [
  {
    path: '',
    component: InstallationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.installation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InstallationDetailComponent,
    resolve: {
      installation: InstallationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.installation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InstallationUpdateComponent,
    resolve: {
      installation: InstallationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.installation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InstallationUpdateComponent,
    resolve: {
      installation: InstallationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.installation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
