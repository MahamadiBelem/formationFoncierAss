import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISfr } from 'app/shared/model/sfr.model';

type EntityResponseType = HttpResponse<ISfr>;
type EntityArrayResponseType = HttpResponse<ISfr[]>;

@Injectable({ providedIn: 'root' })
export class SfrService {
  public resourceUrl = SERVER_API_URL + 'api/sfrs';

  constructor(protected http: HttpClient) {}

  create(sfr: ISfr): Observable<EntityResponseType> {
    return this.http.post<ISfr>(this.resourceUrl, sfr, { observe: 'response' });
  }

  update(sfr: ISfr): Observable<EntityResponseType> {
    return this.http.put<ISfr>(this.resourceUrl, sfr, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISfr>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISfr[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
