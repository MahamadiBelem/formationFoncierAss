import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAmenagement } from 'app/shared/model/amenagement.model';

type EntityResponseType = HttpResponse<IAmenagement>;
type EntityArrayResponseType = HttpResponse<IAmenagement[]>;

@Injectable({ providedIn: 'root' })
export class AmenagementService {
  public resourceUrl = SERVER_API_URL + 'api/amenagements';

  constructor(protected http: HttpClient) {}

  create(amenagement: IAmenagement): Observable<EntityResponseType> {
    return this.http.post<IAmenagement>(this.resourceUrl, amenagement, { observe: 'response' });
  }

  update(amenagement: IAmenagement): Observable<EntityResponseType> {
    return this.http.put<IAmenagement>(this.resourceUrl, amenagement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAmenagement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAmenagement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
