import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';

type EntityResponseType = HttpResponse<INiveauRecrutement>;
type EntityArrayResponseType = HttpResponse<INiveauRecrutement[]>;

@Injectable({ providedIn: 'root' })
export class NiveauRecrutementService {
  public resourceUrl = SERVER_API_URL + 'api/niveau-recrutements';

  constructor(protected http: HttpClient) {}

  create(niveauRecrutement: INiveauRecrutement): Observable<EntityResponseType> {
    return this.http.post<INiveauRecrutement>(this.resourceUrl, niveauRecrutement, { observe: 'response' });
  }

  update(niveauRecrutement: INiveauRecrutement): Observable<EntityResponseType> {
    return this.http.put<INiveauRecrutement>(this.resourceUrl, niveauRecrutement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INiveauRecrutement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INiveauRecrutement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
