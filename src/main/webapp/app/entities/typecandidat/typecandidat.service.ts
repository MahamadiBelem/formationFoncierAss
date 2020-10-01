import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypecandidat } from 'app/shared/model/typecandidat.model';

type EntityResponseType = HttpResponse<ITypecandidat>;
type EntityArrayResponseType = HttpResponse<ITypecandidat[]>;

@Injectable({ providedIn: 'root' })
export class TypecandidatService {
  public resourceUrl = SERVER_API_URL + 'api/typecandidats';

  constructor(protected http: HttpClient) {}

  create(typecandidat: ITypecandidat): Observable<EntityResponseType> {
    return this.http.post<ITypecandidat>(this.resourceUrl, typecandidat, { observe: 'response' });
  }

  update(typecandidat: ITypecandidat): Observable<EntityResponseType> {
    return this.http.put<ITypecandidat>(this.resourceUrl, typecandidat, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypecandidat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypecandidat[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
