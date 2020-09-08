import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';

type EntityResponseType = HttpResponse<ICentreFormation>;
type EntityArrayResponseType = HttpResponse<ICentreFormation[]>;

@Injectable({ providedIn: 'root' })
export class CentreFormationService {
  public resourceUrl = SERVER_API_URL + 'api/centre-formations';

  constructor(protected http: HttpClient) {}

  create(centreFormation: ICentreFormation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(centreFormation);
    return this.http
      .post<ICentreFormation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(centreFormation: ICentreFormation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(centreFormation);
    return this.http
      .put<ICentreFormation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICentreFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICentreFormation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(centreFormation: ICentreFormation): ICentreFormation {
    const copy: ICentreFormation = Object.assign({}, centreFormation, {
      dateOuverture:
        centreFormation.dateOuverture && centreFormation.dateOuverture.isValid()
          ? centreFormation.dateOuverture.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateOuverture = res.body.dateOuverture ? moment(res.body.dateOuverture) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((centreFormation: ICentreFormation) => {
        centreFormation.dateOuverture = centreFormation.dateOuverture ? moment(centreFormation.dateOuverture) : undefined;
      });
    }
    return res;
  }
}
