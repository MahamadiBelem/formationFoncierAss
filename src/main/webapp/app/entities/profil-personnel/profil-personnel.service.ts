import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProfilPersonnel } from 'app/shared/model/profil-personnel.model';

type EntityResponseType = HttpResponse<IProfilPersonnel>;
type EntityArrayResponseType = HttpResponse<IProfilPersonnel[]>;

@Injectable({ providedIn: 'root' })
export class ProfilPersonnelService {
  public resourceUrl = SERVER_API_URL + 'api/profil-personnels';

  constructor(protected http: HttpClient) {}

  create(profilPersonnel: IProfilPersonnel): Observable<EntityResponseType> {
    return this.http.post<IProfilPersonnel>(this.resourceUrl, profilPersonnel, { observe: 'response' });
  }

  update(profilPersonnel: IProfilPersonnel): Observable<EntityResponseType> {
    return this.http.put<IProfilPersonnel>(this.resourceUrl, profilPersonnel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProfilPersonnel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProfilPersonnel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
