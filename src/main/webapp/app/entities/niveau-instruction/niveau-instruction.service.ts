import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INiveauInstruction } from 'app/shared/model/niveau-instruction.model';

type EntityResponseType = HttpResponse<INiveauInstruction>;
type EntityArrayResponseType = HttpResponse<INiveauInstruction[]>;

@Injectable({ providedIn: 'root' })
export class NiveauInstructionService {
  public resourceUrl = SERVER_API_URL + 'api/niveau-instructions';

  constructor(protected http: HttpClient) {}

  create(niveauInstruction: INiveauInstruction): Observable<EntityResponseType> {
    return this.http.post<INiveauInstruction>(this.resourceUrl, niveauInstruction, { observe: 'response' });
  }

  update(niveauInstruction: INiveauInstruction): Observable<EntityResponseType> {
    return this.http.put<INiveauInstruction>(this.resourceUrl, niveauInstruction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INiveauInstruction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INiveauInstruction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
