import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVillages } from 'app/shared/model/villages.model';

type EntityResponseType = HttpResponse<IVillages>;
type EntityArrayResponseType = HttpResponse<IVillages[]>;

@Injectable({ providedIn: 'root' })
export class VillagesService {
  public resourceUrl = SERVER_API_URL + 'api/villages';

  constructor(protected http: HttpClient) {}

  create(villages: IVillages): Observable<EntityResponseType> {
    return this.http.post<IVillages>(this.resourceUrl, villages, { observe: 'response' });
  }

  update(villages: IVillages): Observable<EntityResponseType> {
    return this.http.put<IVillages>(this.resourceUrl, villages, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVillages>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVillages[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
