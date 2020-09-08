import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INiveauInstruction, NiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { NiveauInstructionService } from './niveau-instruction.service';
import { NiveauInstructionComponent } from './niveau-instruction.component';
import { NiveauInstructionDetailComponent } from './niveau-instruction-detail.component';
import { NiveauInstructionUpdateComponent } from './niveau-instruction-update.component';

@Injectable({ providedIn: 'root' })
export class NiveauInstructionResolve implements Resolve<INiveauInstruction> {
  constructor(private service: NiveauInstructionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INiveauInstruction> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((niveauInstruction: HttpResponse<NiveauInstruction>) => {
          if (niveauInstruction.body) {
            return of(niveauInstruction.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NiveauInstruction());
  }
}

export const niveauInstructionRoute: Routes = [
  {
    path: '',
    component: NiveauInstructionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauInstruction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NiveauInstructionDetailComponent,
    resolve: {
      niveauInstruction: NiveauInstructionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauInstruction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NiveauInstructionUpdateComponent,
    resolve: {
      niveauInstruction: NiveauInstructionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauInstruction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NiveauInstructionUpdateComponent,
    resolve: {
      niveauInstruction: NiveauInstructionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionFormationApp.niveauInstruction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
