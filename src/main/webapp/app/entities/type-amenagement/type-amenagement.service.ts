import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeAmenagement } from 'app/shared/model/type-amenagement.model';

type EntityResponseType = HttpResponse<ITypeAmenagement>;
type EntityArrayResponseType = HttpResponse<ITypeAmenagement[]>;

@Injectable({ providedIn: 'root' })
export class TypeAmenagementService {
  public resourceUrl = SERVER_API_URL + 'api/type-amenagements';

  constructor(protected http: HttpClient) {}

  create(typeAmenagement: ITypeAmenagement): Observable<EntityResponseType> {
    return this.http.post<ITypeAmenagement>(this.resourceUrl, typeAmenagement, { observe: 'response' });
  }

  update(typeAmenagement: ITypeAmenagement): Observable<EntityResponseType> {
    return this.http.put<ITypeAmenagement>(this.resourceUrl, typeAmenagement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeAmenagement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeAmenagement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
