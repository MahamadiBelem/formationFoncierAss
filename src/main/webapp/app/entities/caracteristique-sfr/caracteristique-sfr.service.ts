import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';

type EntityResponseType = HttpResponse<ICaracteristiqueSfr>;
type EntityArrayResponseType = HttpResponse<ICaracteristiqueSfr[]>;

@Injectable({ providedIn: 'root' })
export class CaracteristiqueSfrService {
  public resourceUrl = SERVER_API_URL + 'api/caracteristique-sfrs';

  constructor(protected http: HttpClient) {}

  create(caracteristiqueSfr: ICaracteristiqueSfr): Observable<EntityResponseType> {
    return this.http.post<ICaracteristiqueSfr>(this.resourceUrl, caracteristiqueSfr, { observe: 'response' });
  }

  update(caracteristiqueSfr: ICaracteristiqueSfr): Observable<EntityResponseType> {
    return this.http.put<ICaracteristiqueSfr>(this.resourceUrl, caracteristiqueSfr, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICaracteristiqueSfr>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICaracteristiqueSfr[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
