import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAmenagement, Amenagement } from 'app/shared/model/amenagement.model';
import { AmenagementService } from './amenagement.service';
import { AmenagementComponent } from './amenagement.component';
import { AmenagementDetailComponent } from './amenagement-detail.component';
import { AmenagementUpdateComponent } from './amenagement-update.component';

@Injectable({ providedIn: 'root' })
export class AmenagementResolve implements Resolve<IAmenagement> {
  constructor(private service: AmenagementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAmenagement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((amenagement: HttpResponse<Amenagement>) => {
          if (amenagement.body) {
            return of(amenagement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Amenagement());
  }
}

export const amenagementRoute: Routes = [
  {
    path: '',
    component: AmenagementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gestionFormationApp.amenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AmenagementDetailComponent,
    resolve: {
      amenagement: AmenagementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.amenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AmenagementUpdateComponent,
    resolve: {
      amenagement: AmenagementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.amenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AmenagementUpdateComponent,
    resolve: {
      amenagement: AmenagementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.amenagement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
