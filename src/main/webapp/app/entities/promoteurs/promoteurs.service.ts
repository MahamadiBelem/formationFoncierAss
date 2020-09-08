import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPromoteurs } from 'app/shared/model/promoteurs.model';

type EntityResponseType = HttpResponse<IPromoteurs>;
type EntityArrayResponseType = HttpResponse<IPromoteurs[]>;

@Injectable({ providedIn: 'root' })
export class PromoteursService {
  public resourceUrl = SERVER_API_URL + 'api/promoteurs';

  constructor(protected http: HttpClient) {}

  create(promoteurs: IPromoteurs): Observable<EntityResponseType> {
    return this.http.post<IPromoteurs>(this.resourceUrl, promoteurs, { observe: 'response' });
  }

  update(promoteurs: IPromoteurs): Observable<EntityResponseType> {
    return this.http.put<IPromoteurs>(this.resourceUrl, promoteurs, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPromoteurs>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPromoteurs[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
