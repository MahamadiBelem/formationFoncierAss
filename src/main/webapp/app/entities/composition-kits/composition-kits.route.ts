import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompositionKits, CompositionKits } from 'app/shared/model/composition-kits.model';
import { CompositionKitsService } from './composition-kits.service';
import { CompositionKitsComponent } from './composition-kits.component';
import { CompositionKitsDetailComponent } from './composition-kits-detail.component';
import { CompositionKitsUpdateComponent } from './composition-kits-update.component';

@Injectable({ providedIn: 'root' })
export class CompositionKitsResolve implements Resolve<ICompositionKits> {
  constructor(private service: CompositionKitsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompositionKits> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((compositionKits: HttpResponse<CompositionKits>) => {
          if (compositionKits.body) {
            return of(compositionKits.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CompositionKits());
  }
}

export const compositionKitsRoute: Routes = [
  {
    path: '',
    component: CompositionKitsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.compositionKits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CompositionKitsDetailComponent,
    resolve: {
      compositionKits: CompositionKitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.compositionKits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CompositionKitsUpdateComponent,
    resolve: {
      compositionKits: CompositionKitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.compositionKits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CompositionKitsUpdateComponent,
    resolve: {
      compositionKits: CompositionKitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.compositionKits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
