import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeDemandeur } from 'app/shared/model/type-demandeur.model';

type EntityResponseType = HttpResponse<ITypeDemandeur>;
type EntityArrayResponseType = HttpResponse<ITypeDemandeur[]>;

@Injectable({ providedIn: 'root' })
export class TypeDemandeurService {
  public resourceUrl = SERVER_API_URL + 'api/type-demandeurs';

  constructor(protected http: HttpClient) {}

  create(typeDemandeur: ITypeDemandeur): Observable<EntityResponseType> {
    return this.http.post<ITypeDemandeur>(this.resourceUrl, typeDemandeur, { observe: 'response' });
  }

  update(typeDemandeur: ITypeDemandeur): Observable<EntityResponseType> {
    return this.http.put<ITypeDemandeur>(this.resourceUrl, typeDemandeur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeDemandeur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeDemandeur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
