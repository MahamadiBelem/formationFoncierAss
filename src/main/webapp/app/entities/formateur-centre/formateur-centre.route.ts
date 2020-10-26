import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormateurCentre, FormateurCentre } from 'app/shared/model/formateur-centre.model';
import { FormateurCentreService } from './formateur-centre.service';
import { FormateurCentreComponent } from './formateur-centre.component';
import { FormateurCentreDetailComponent } from './formateur-centre-detail.component';
import { FormateurCentreUpdateComponent } from './formateur-centre-update.component';

@Injectable({ providedIn: 'root' })
export class FormateurCentreResolve implements Resolve<IFormateurCentre> {
  constructor(private service: FormateurCentreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormateurCentre> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formateurCentre: HttpResponse<FormateurCentre>) => {
          if (formateurCentre.body) {
            return of(formateurCentre.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FormateurCentre());
  }
}

export const formateurCentreRoute: Routes = [
  {
    path: '',
    component: FormateurCentreComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.formateurCentre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormateurCentreDetailComponent,
    resolve: {
      formateurCentre: FormateurCentreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurCentre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormateurCentreUpdateComponent,
    resolve: {
      formateurCentre: FormateurCentreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurCentre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormateurCentreUpdateComponent,
    resolve: {
      formateurCentre: FormateurCentreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formateurCentre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
