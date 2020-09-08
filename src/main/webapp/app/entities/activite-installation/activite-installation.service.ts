import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';

type EntityResponseType = HttpResponse<IActiviteInstallation>;
type EntityArrayResponseType = HttpResponse<IActiviteInstallation[]>;

@Injectable({ providedIn: 'root' })
export class ActiviteInstallationService {
  public resourceUrl = SERVER_API_URL + 'api/activite-installations';

  constructor(protected http: HttpClient) {}

  create(activiteInstallation: IActiviteInstallation): Observable<EntityResponseType> {
    return this.http.post<IActiviteInstallation>(this.resourceUrl, activiteInstallation, { observe: 'response' });
  }

  update(activiteInstallation: IActiviteInstallation): Observable<EntityResponseType> {
    return this.http.put<IActiviteInstallation>(this.resourceUrl, activiteInstallation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IActiviteInstallation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IActiviteInstallation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
