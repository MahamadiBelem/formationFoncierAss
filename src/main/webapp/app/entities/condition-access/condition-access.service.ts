import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConditionAccess } from 'app/shared/model/condition-access.model';

type EntityResponseType = HttpResponse<IConditionAccess>;
type EntityArrayResponseType = HttpResponse<IConditionAccess[]>;

@Injectable({ providedIn: 'root' })
export class ConditionAccessService {
  public resourceUrl = SERVER_API_URL + 'api/condition-accesses';

  constructor(protected http: HttpClient) {}

  create(conditionAccess: IConditionAccess): Observable<EntityResponseType> {
    return this.http.post<IConditionAccess>(this.resourceUrl, conditionAccess, { observe: 'response' });
  }

  update(conditionAccess: IConditionAccess): Observable<EntityResponseType> {
    return this.http.put<IConditionAccess>(this.resourceUrl, conditionAccess, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConditionAccess>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConditionAccess[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
