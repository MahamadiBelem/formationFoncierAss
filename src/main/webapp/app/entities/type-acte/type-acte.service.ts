import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeActe } from 'app/shared/model/type-acte.model';

type EntityResponseType = HttpResponse<ITypeActe>;
type EntityArrayResponseType = HttpResponse<ITypeActe[]>;

@Injectable({ providedIn: 'root' })
export class TypeActeService {
  public resourceUrl = SERVER_API_URL + 'api/type-actes';

  constructor(protected http: HttpClient) {}

  create(typeActe: ITypeActe): Observable<EntityResponseType> {
    return this.http.post<ITypeActe>(this.resourceUrl, typeActe, { observe: 'response' });
  }

  update(typeActe: ITypeActe): Observable<EntityResponseType> {
    return this.http.put<ITypeActe>(this.resourceUrl, typeActe, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeActe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeActe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
