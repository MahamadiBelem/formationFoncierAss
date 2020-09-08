import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInstallation } from 'app/shared/model/installation.model';

type EntityResponseType = HttpResponse<IInstallation>;
type EntityArrayResponseType = HttpResponse<IInstallation[]>;

@Injectable({ providedIn: 'root' })
export class InstallationService {
  public resourceUrl = SERVER_API_URL + 'api/installations';

  constructor(protected http: HttpClient) {}

  create(installation: IInstallation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(installation);
    return this.http
      .post<IInstallation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(installation: IInstallation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(installation);
    return this.http
      .put<IInstallation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInstallation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInstallation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(installation: IInstallation): IInstallation {
    const copy: IInstallation = Object.assign({}, installation, {
      dateIntallation:
        installation.dateIntallation && installation.dateIntallation.isValid()
          ? installation.dateIntallation.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateIntallation = res.body.dateIntallation ? moment(res.body.dateIntallation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((installation: IInstallation) => {
        installation.dateIntallation = installation.dateIntallation ? moment(installation.dateIntallation) : undefined;
      });
    }
    return res;
  }
}
