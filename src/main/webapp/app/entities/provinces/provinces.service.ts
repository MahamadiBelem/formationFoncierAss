import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProvinces } from 'app/shared/model/provinces.model';

type EntityResponseType = HttpResponse<IProvinces>;
type EntityArrayResponseType = HttpResponse<IProvinces[]>;

@Injectable({ providedIn: 'root' })
export class ProvincesService {
  public resourceUrl = SERVER_API_URL + 'api/provinces';

  constructor(protected http: HttpClient) {}

  create(provinces: IProvinces): Observable<EntityResponseType> {
    return this.http.post<IProvinces>(this.resourceUrl, provinces, { observe: 'response' });
  }

  update(provinces: IProvinces): Observable<EntityResponseType> {
    return this.http.put<IProvinces>(this.resourceUrl, provinces, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProvinces>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProvinces[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
