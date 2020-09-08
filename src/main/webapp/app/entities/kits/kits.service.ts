import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKits } from 'app/shared/model/kits.model';

type EntityResponseType = HttpResponse<IKits>;
type EntityArrayResponseType = HttpResponse<IKits[]>;

@Injectable({ providedIn: 'root' })
export class KitsService {
  public resourceUrl = SERVER_API_URL + 'api/kits';

  constructor(protected http: HttpClient) {}

  create(kits: IKits): Observable<EntityResponseType> {
    return this.http.post<IKits>(this.resourceUrl, kits, { observe: 'response' });
  }

  update(kits: IKits): Observable<EntityResponseType> {
    return this.http.put<IKits>(this.resourceUrl, kits, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IKits>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IKits[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
