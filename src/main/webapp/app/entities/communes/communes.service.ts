import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommunes } from 'app/shared/model/communes.model';

type EntityResponseType = HttpResponse<ICommunes>;
type EntityArrayResponseType = HttpResponse<ICommunes[]>;

@Injectable({ providedIn: 'root' })
export class CommunesService {
  public resourceUrl = SERVER_API_URL + 'api/communes';

  constructor(protected http: HttpClient) {}

  create(communes: ICommunes): Observable<EntityResponseType> {
    return this.http.post<ICommunes>(this.resourceUrl, communes, { observe: 'response' });
  }

  update(communes: ICommunes): Observable<EntityResponseType> {
    return this.http.put<ICommunes>(this.resourceUrl, communes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICommunes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICommunes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
