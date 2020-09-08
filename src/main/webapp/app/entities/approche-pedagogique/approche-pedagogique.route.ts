import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IApprochePedagogique, ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { ApprochePedagogiqueService } from './approche-pedagogique.service';
import { ApprochePedagogiqueComponent } from './approche-pedagogique.component';
import { ApprochePedagogiqueDetailComponent } from './approche-pedagogique-detail.component';
import { ApprochePedagogiqueUpdateComponent } from './approche-pedagogique-update.component';

@Injectable({ providedIn: 'root' })
export class ApprochePedagogiqueResolve implements Resolve<IApprochePedagogique> {
  constructor(private service: ApprochePedagogiqueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IApprochePedagogique> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((approchePedagogique: HttpResponse<ApprochePedagogique>) => {
          if (approchePedagogique.body) {
            return of(approchePedagogique.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ApprochePedagogique());
  }
}

export const approchePedagogiqueRoute: Routes = [
  {
    path: '',
    component: ApprochePedagogiqueComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.approchePedagogique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApprochePedagogiqueDetailComponent,
    resolve: {
      approchePedagogique: ApprochePedagogiqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.approchePedagogique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApprochePedagogiqueUpdateComponent,
    resolve: {
      approchePedagogique: ApprochePedagogiqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.approchePedagogique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApprochePedagogiqueUpdateComponent,
    resolve: {
      approchePedagogique: ApprochePedagogiqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.approchePedagogique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
