import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISpecialites } from 'app/shared/model/specialites.model';

type EntityResponseType = HttpResponse<ISpecialites>;
type EntityArrayResponseType = HttpResponse<ISpecialites[]>;

@Injectable({ providedIn: 'root' })
export class SpecialitesService {
  public resourceUrl = SERVER_API_URL + 'api/specialites';

  constructor(protected http: HttpClient) {}

  create(specialites: ISpecialites): Observable<EntityResponseType> {
    return this.http.post<ISpecialites>(this.resourceUrl, specialites, { observe: 'response' });
  }

  update(specialites: ISpecialites): Observable<EntityResponseType> {
    return this.http.put<ISpecialites>(this.resourceUrl, specialites, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpecialites>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpecialites[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
