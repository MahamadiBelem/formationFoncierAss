import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeInfratructure } from 'app/shared/model/type-infratructure.model';

type EntityResponseType = HttpResponse<ITypeInfratructure>;
type EntityArrayResponseType = HttpResponse<ITypeInfratructure[]>;

@Injectable({ providedIn: 'root' })
export class TypeInfratructureService {
  public resourceUrl = SERVER_API_URL + 'api/type-infratructures';

  constructor(protected http: HttpClient) {}

  create(typeInfratructure: ITypeInfratructure): Observable<EntityResponseType> {
    return this.http.post<ITypeInfratructure>(this.resourceUrl, typeInfratructure, { observe: 'response' });
  }

  update(typeInfratructure: ITypeInfratructure): Observable<EntityResponseType> {
    return this.http.put<ITypeInfratructure>(this.resourceUrl, typeInfratructure, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeInfratructure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeInfratructure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
