import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApprenantes } from 'app/shared/model/apprenantes.model';

type EntityResponseType = HttpResponse<IApprenantes>;
type EntityArrayResponseType = HttpResponse<IApprenantes[]>;

@Injectable({ providedIn: 'root' })
export class ApprenantesService {
  public resourceUrl = SERVER_API_URL + 'api/apprenantes';

  constructor(protected http: HttpClient) {}

  create(apprenantes: IApprenantes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(apprenantes);
    return this.http
      .post<IApprenantes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(apprenantes: IApprenantes): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(apprenantes);
    return this.http
      .put<IApprenantes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IApprenantes>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IApprenantes[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(apprenantes: IApprenantes): IApprenantes {
    const copy: IApprenantes = Object.assign({}, apprenantes, {
      dateNaissance:
        apprenantes.dateNaissance && apprenantes.dateNaissance.isValid() ? apprenantes.dateNaissance.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateNaissance = res.body.dateNaissance ? moment(res.body.dateNaissance) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((apprenantes: IApprenantes) => {
        apprenantes.dateNaissance = apprenantes.dateNaissance ? moment(apprenantes.dateNaissance) : undefined;
      });
    }
    return res;
  }
}
