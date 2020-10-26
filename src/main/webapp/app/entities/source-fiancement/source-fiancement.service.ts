import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISourceFiancement } from 'app/shared/model/source-fiancement.model';

type EntityResponseType = HttpResponse<ISourceFiancement>;
type EntityArrayResponseType = HttpResponse<ISourceFiancement[]>;

@Injectable({ providedIn: 'root' })
export class SourceFiancementService {
  public resourceUrl = SERVER_API_URL + 'api/source-fiancements';

  constructor(protected http: HttpClient) {}

  create(sourceFiancement: ISourceFiancement): Observable<EntityResponseType> {
    return this.http.post<ISourceFiancement>(this.resourceUrl, sourceFiancement, { observe: 'response' });
  }

  update(sourceFiancement: ISourceFiancement): Observable<EntityResponseType> {
    return this.http.put<ISourceFiancement>(this.resourceUrl, sourceFiancement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISourceFiancement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISourceFiancement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
