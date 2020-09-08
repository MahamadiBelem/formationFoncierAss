import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeFormation } from 'app/shared/model/type-formation.model';

type EntityResponseType = HttpResponse<ITypeFormation>;
type EntityArrayResponseType = HttpResponse<ITypeFormation[]>;

@Injectable({ providedIn: 'root' })
export class TypeFormationService {
  public resourceUrl = SERVER_API_URL + 'api/type-formations';

  constructor(protected http: HttpClient) {}

  create(typeFormation: ITypeFormation): Observable<EntityResponseType> {
    return this.http.post<ITypeFormation>(this.resourceUrl, typeFormation, { observe: 'response' });
  }

  update(typeFormation: ITypeFormation): Observable<EntityResponseType> {
    return this.http.put<ITypeFormation>(this.resourceUrl, typeFormation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeFormation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
