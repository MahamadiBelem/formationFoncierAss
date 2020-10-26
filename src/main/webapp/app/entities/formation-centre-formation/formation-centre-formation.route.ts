import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormationCentreFormation, FormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';
import { FormationCentreFormationService } from './formation-centre-formation.service';
import { FormationCentreFormationComponent } from './formation-centre-formation.component';
import { FormationCentreFormationDetailComponent } from './formation-centre-formation-detail.component';
import { FormationCentreFormationUpdateComponent } from './formation-centre-formation-update.component';

@Injectable({ providedIn: 'root' })
export class FormationCentreFormationResolve implements Resolve<IFormationCentreFormation> {
  constructor(private service: FormationCentreFormationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormationCentreFormation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formationCentreFormation: HttpResponse<FormationCentreFormation>) => {
          if (formationCentreFormation.body) {
            return of(formationCentreFormation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FormationCentreFormation());
  }
}

export const formationCentreFormationRoute: Routes = [
  {
    path: '',
    component: FormationCentreFormationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.formationCentreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormationCentreFormationDetailComponent,
    resolve: {
      formationCentreFormation: FormationCentreFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formationCentreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormationCentreFormationUpdateComponent,
    resolve: {
      formationCentreFormation: FormationCentreFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formationCentreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormationCentreFormationUpdateComponent,
    resolve: {
      formationCentreFormation: FormationCentreFormationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.formationCentreFormation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
