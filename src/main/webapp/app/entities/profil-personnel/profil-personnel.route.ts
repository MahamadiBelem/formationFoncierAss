import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProfilPersonnel, ProfilPersonnel } from 'app/shared/model/profil-personnel.model';
import { ProfilPersonnelService } from './profil-personnel.service';
import { ProfilPersonnelComponent } from './profil-personnel.component';
import { ProfilPersonnelDetailComponent } from './profil-personnel-detail.component';
import { ProfilPersonnelUpdateComponent } from './profil-personnel-update.component';

@Injectable({ providedIn: 'root' })
export class ProfilPersonnelResolve implements Resolve<IProfilPersonnel> {
  constructor(private service: ProfilPersonnelService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProfilPersonnel> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((profilPersonnel: HttpResponse<ProfilPersonnel>) => {
          if (profilPersonnel.body) {
            return of(profilPersonnel.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProfilPersonnel());
  }
}

export const profilPersonnelRoute: Routes = [
  {
    path: '',
    component: ProfilPersonnelComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.profilPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProfilPersonnelDetailComponent,
    resolve: {
      profilPersonnel: ProfilPersonnelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.profilPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProfilPersonnelUpdateComponent,
    resolve: {
      profilPersonnel: ProfilPersonnelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.profilPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProfilPersonnelUpdateComponent,
    resolve: {
      profilPersonnel: ProfilPersonnelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.profilPersonnel.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
