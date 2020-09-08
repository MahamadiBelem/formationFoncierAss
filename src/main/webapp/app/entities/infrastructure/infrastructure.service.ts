import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInfrastructure } from 'app/shared/model/infrastructure.model';

type EntityResponseType = HttpResponse<IInfrastructure>;
type EntityArrayResponseType = HttpResponse<IInfrastructure[]>;

@Injectable({ providedIn: 'root' })
export class InfrastructureService {
  public resourceUrl = SERVER_API_URL + 'api/infrastructures';

  constructor(protected http: HttpClient) {}

  create(infrastructure: IInfrastructure): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(infrastructure);
    return this.http
      .post<IInfrastructure>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(infrastructure: IInfrastructure): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(infrastructure);
    return this.http
      .put<IInfrastructure>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInfrastructure>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInfrastructure[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(infrastructure: IInfrastructure): IInfrastructure {
    const copy: IInfrastructure = Object.assign({}, infrastructure, {
      dateElaboration:
        infrastructure.dateElaboration && infrastructure.dateElaboration.isValid()
          ? infrastructure.dateElaboration.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateElaboration = res.body.dateElaboration ? moment(res.body.dateElaboration) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((infrastructure: IInfrastructure) => {
        infrastructure.dateElaboration = infrastructure.dateElaboration ? moment(infrastructure.dateElaboration) : undefined;
      });
    }
    return res;
  }
}
