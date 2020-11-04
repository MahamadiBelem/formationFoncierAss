import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormationSFR } from 'app/shared/model/formation-sfr.model';

type EntityResponseType = HttpResponse<IFormationSFR>;
type EntityArrayResponseType = HttpResponse<IFormationSFR[]>;

@Injectable({ providedIn: 'root' })
export class FormationSFRService {
  public resourceUrl = SERVER_API_URL + 'api/formation-sfrs';

  constructor(protected http: HttpClient) {}

  create(formationSFR: IFormationSFR): Observable<EntityResponseType> {
    return this.http.post<IFormationSFR>(this.resourceUrl, formationSFR, { observe: 'response' });
  }

  update(formationSFR: IFormationSFR): Observable<EntityResponseType> {
    return this.http.put<IFormationSFR>(this.resourceUrl, formationSFR, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFormationSFR>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormationSFR[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
