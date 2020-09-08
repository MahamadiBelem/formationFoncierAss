import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';

type EntityResponseType = HttpResponse<IApprochePedagogique>;
type EntityArrayResponseType = HttpResponse<IApprochePedagogique[]>;

@Injectable({ providedIn: 'root' })
export class ApprochePedagogiqueService {
  public resourceUrl = SERVER_API_URL + 'api/approche-pedagogiques';

  constructor(protected http: HttpClient) {}

  create(approchePedagogique: IApprochePedagogique): Observable<EntityResponseType> {
    return this.http.post<IApprochePedagogique>(this.resourceUrl, approchePedagogique, { observe: 'response' });
  }

  update(approchePedagogique: IApprochePedagogique): Observable<EntityResponseType> {
    return this.http.put<IApprochePedagogique>(this.resourceUrl, approchePedagogique, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApprochePedagogique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApprochePedagogique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
