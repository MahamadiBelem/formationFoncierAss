import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPublicCible } from 'app/shared/model/public-cible.model';

type EntityResponseType = HttpResponse<IPublicCible>;
type EntityArrayResponseType = HttpResponse<IPublicCible[]>;

@Injectable({ providedIn: 'root' })
export class PublicCibleService {
  public resourceUrl = SERVER_API_URL + 'api/public-cibles';

  constructor(protected http: HttpClient) {}

  create(publicCible: IPublicCible): Observable<EntityResponseType> {
    return this.http.post<IPublicCible>(this.resourceUrl, publicCible, { observe: 'response' });
  }

  update(publicCible: IPublicCible): Observable<EntityResponseType> {
    return this.http.put<IPublicCible>(this.resourceUrl, publicCible, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPublicCible>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPublicCible[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
