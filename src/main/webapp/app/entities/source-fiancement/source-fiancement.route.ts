import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISourceFiancement, SourceFiancement } from 'app/shared/model/source-fiancement.model';
import { SourceFiancementService } from './source-fiancement.service';
import { SourceFiancementComponent } from './source-fiancement.component';
import { SourceFiancementDetailComponent } from './source-fiancement-detail.component';
import { SourceFiancementUpdateComponent } from './source-fiancement-update.component';

@Injectable({ providedIn: 'root' })
export class SourceFiancementResolve implements Resolve<ISourceFiancement> {
  constructor(private service: SourceFiancementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISourceFiancement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sourceFiancement: HttpResponse<SourceFiancement>) => {
          if (sourceFiancement.body) {
            return of(sourceFiancement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SourceFiancement());
  }
}

export const sourceFiancementRoute: Routes = [
  {
    path: '',
    component: SourceFiancementComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sourceFiancement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SourceFiancementDetailComponent,
    resolve: {
      sourceFiancement: SourceFiancementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sourceFiancement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SourceFiancementUpdateComponent,
    resolve: {
      sourceFiancement: SourceFiancementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sourceFiancement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SourceFiancementUpdateComponent,
    resolve: {
      sourceFiancement: SourceFiancementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.sourceFiancement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
