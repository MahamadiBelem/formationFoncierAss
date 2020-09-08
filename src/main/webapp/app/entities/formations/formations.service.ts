import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormations } from 'app/shared/model/formations.model';

type EntityResponseType = HttpResponse<IFormations>;
type EntityArrayResponseType = HttpResponse<IFormations[]>;

@Injectable({ providedIn: 'root' })
export class FormationsService {
  public resourceUrl = SERVER_API_URL + 'api/formations';

  constructor(protected http: HttpClient) {}

  create(formations: IFormations): Observable<EntityResponseType> {
    return this.http.post<IFormations>(this.resourceUrl, formations, { observe: 'response' });
  }

  update(formations: IFormations): Observable<EntityResponseType> {
    return this.http.put<IFormations>(this.resourceUrl, formations, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFormations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormations[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
