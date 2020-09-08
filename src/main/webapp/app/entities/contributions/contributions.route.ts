import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContributions, Contributions } from 'app/shared/model/contributions.model';
import { ContributionsService } from './contributions.service';
import { ContributionsComponent } from './contributions.component';
import { ContributionsDetailComponent } from './contributions-detail.component';
import { ContributionsUpdateComponent } from './contributions-update.component';

@Injectable({ providedIn: 'root' })
export class ContributionsResolve implements Resolve<IContributions> {
  constructor(private service: ContributionsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContributions> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((contributions: HttpResponse<Contributions>) => {
          if (contributions.body) {
            return of(contributions.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Contributions());
  }
}

export const contributionsRoute: Routes = [
  {
    path: '',
    component: ContributionsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.contributions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ContributionsDetailComponent,
    resolve: {
      contributions: ContributionsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.contributions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ContributionsUpdateComponent,
    resolve: {
      contributions: ContributionsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.contributions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ContributionsUpdateComponent,
    resolve: {
      contributions: ContributionsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.contributions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
