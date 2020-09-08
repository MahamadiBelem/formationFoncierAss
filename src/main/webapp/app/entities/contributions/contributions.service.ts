import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContributions } from 'app/shared/model/contributions.model';

type EntityResponseType = HttpResponse<IContributions>;
type EntityArrayResponseType = HttpResponse<IContributions[]>;

@Injectable({ providedIn: 'root' })
export class ContributionsService {
  public resourceUrl = SERVER_API_URL + 'api/contributions';

  constructor(protected http: HttpClient) {}

  create(contributions: IContributions): Observable<EntityResponseType> {
    return this.http.post<IContributions>(this.resourceUrl, contributions, { observe: 'response' });
  }

  update(contributions: IContributions): Observable<EntityResponseType> {
    return this.http.put<IContributions>(this.resourceUrl, contributions, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IContributions>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContributions[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
