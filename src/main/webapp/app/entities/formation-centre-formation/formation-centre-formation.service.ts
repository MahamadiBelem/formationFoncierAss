import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';

type EntityResponseType = HttpResponse<IFormationCentreFormation>;
type EntityArrayResponseType = HttpResponse<IFormationCentreFormation[]>;

@Injectable({ providedIn: 'root' })
export class FormationCentreFormationService {
  public resourceUrl = SERVER_API_URL + 'api/formation-centre-formations';

  constructor(protected http: HttpClient) {}

  create(formationCentreFormation: IFormationCentreFormation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formationCentreFormation);
    return this.http
      .post<IFormationCentreFormation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(formationCentreFormation: IFormationCentreFormation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formationCentreFormation);
    return this.http
      .put<IFormationCentreFormation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFormationCentreFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFormationCentreFormation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(formationCentreFormation: IFormationCentreFormation): IFormationCentreFormation {
    const copy: IFormationCentreFormation = Object.assign({}, formationCentreFormation, {
      datedebut:
        formationCentreFormation.datedebut && formationCentreFormation.datedebut.isValid()
          ? formationCentreFormation.datedebut.format(DATE_FORMAT)
          : undefined,
      dateClore:
        formationCentreFormation.dateClore && formationCentreFormation.dateClore.isValid()
          ? formationCentreFormation.dateClore.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datedebut = res.body.datedebut ? moment(res.body.datedebut) : undefined;
      res.body.dateClore = res.body.dateClore ? moment(res.body.dateClore) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((formationCentreFormation: IFormationCentreFormation) => {
        formationCentreFormation.datedebut = formationCentreFormation.datedebut ? moment(formationCentreFormation.datedebut) : undefined;
        formationCentreFormation.dateClore = formationCentreFormation.dateClore ? moment(formationCentreFormation.dateClore) : undefined;
      });
    }
    return res;
  }
}
