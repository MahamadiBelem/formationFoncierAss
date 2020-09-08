import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormateurs } from 'app/shared/model/formateurs.model';

type EntityResponseType = HttpResponse<IFormateurs>;
type EntityArrayResponseType = HttpResponse<IFormateurs[]>;

@Injectable({ providedIn: 'root' })
export class FormateursService {
  public resourceUrl = SERVER_API_URL + 'api/formateurs';

  constructor(protected http: HttpClient) {}

  create(formateurs: IFormateurs): Observable<EntityResponseType> {
    return this.http.post<IFormateurs>(this.resourceUrl, formateurs, { observe: 'response' });
  }

  update(formateurs: IFormateurs): Observable<EntityResponseType> {
    return this.http.put<IFormateurs>(this.resourceUrl, formateurs, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFormateurs>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormateurs[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
