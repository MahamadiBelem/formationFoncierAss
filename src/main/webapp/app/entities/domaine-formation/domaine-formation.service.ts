import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDomaineFormation } from 'app/shared/model/domaine-formation.model';

type EntityResponseType = HttpResponse<IDomaineFormation>;
type EntityArrayResponseType = HttpResponse<IDomaineFormation[]>;

@Injectable({ providedIn: 'root' })
export class DomaineFormationService {
  public resourceUrl = SERVER_API_URL + 'api/domaine-formations';

  constructor(protected http: HttpClient) {}

  create(domaineFormation: IDomaineFormation): Observable<EntityResponseType> {
    return this.http.post<IDomaineFormation>(this.resourceUrl, domaineFormation, { observe: 'response' });
  }

  update(domaineFormation: IDomaineFormation): Observable<EntityResponseType> {
    return this.http.put<IDomaineFormation>(this.resourceUrl, domaineFormation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDomaineFormation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDomaineFormation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
