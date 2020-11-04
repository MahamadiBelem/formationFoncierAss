import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDossierApfr } from 'app/shared/model/dossier-apfr.model';

type EntityResponseType = HttpResponse<IDossierApfr>;
type EntityArrayResponseType = HttpResponse<IDossierApfr[]>;

@Injectable({ providedIn: 'root' })
export class DossierApfrService {
  public resourceUrl = SERVER_API_URL + 'api/dossier-apfrs';

  constructor(protected http: HttpClient) {}

  create(dossierApfr: IDossierApfr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dossierApfr);
    return this.http
      .post<IDossierApfr>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dossierApfr: IDossierApfr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dossierApfr);
    return this.http
      .put<IDossierApfr>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDossierApfr>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDossierApfr[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dossierApfr: IDossierApfr): IDossierApfr {
    const copy: IDossierApfr = Object.assign({}, dossierApfr, {
      dateReceptionCfv:
        dossierApfr.dateReceptionCfv && dossierApfr.dateReceptionCfv.isValid()
          ? dossierApfr.dateReceptionCfv.format(DATE_FORMAT)
          : undefined,
      dateRetourCt:
        dossierApfr.dateRetourCt && dossierApfr.dateRetourCt.isValid() ? dossierApfr.dateRetourCt.format(DATE_FORMAT) : undefined,
      dateEnregistrement:
        dossierApfr.dateEnregistrement && dossierApfr.dateEnregistrement.isValid()
          ? dossierApfr.dateEnregistrement.format(DATE_FORMAT)
          : undefined,
      dateDepotSt: dossierApfr.dateDepotSt && dossierApfr.dateDepotSt.isValid() ? dossierApfr.dateDepotSt.format(DATE_FORMAT) : undefined,
      dateRetourSt:
        dossierApfr.dateRetourSt && dossierApfr.dateRetourSt.isValid() ? dossierApfr.dateRetourSt.format(DATE_FORMAT) : undefined,
      dateNotification:
        dossierApfr.dateNotification && dossierApfr.dateNotification.isValid()
          ? dossierApfr.dateNotification.format(DATE_FORMAT)
          : undefined,
      dateSignature:
        dossierApfr.dateSignature && dossierApfr.dateSignature.isValid() ? dossierApfr.dateSignature.format(DATE_FORMAT) : undefined,
      taxesTotale: dossierApfr.taxesTotale && dossierApfr.taxesTotale.isValid() ? dossierApfr.taxesTotale.format(DATE_FORMAT) : undefined,
      dateConstatation:
        dossierApfr.dateConstatation && dossierApfr.dateConstatation.isValid()
          ? dossierApfr.dateConstatation.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateReceptionCfv = res.body.dateReceptionCfv ? moment(res.body.dateReceptionCfv) : undefined;
      res.body.dateRetourCt = res.body.dateRetourCt ? moment(res.body.dateRetourCt) : undefined;
      res.body.dateEnregistrement = res.body.dateEnregistrement ? moment(res.body.dateEnregistrement) : undefined;
      res.body.dateDepotSt = res.body.dateDepotSt ? moment(res.body.dateDepotSt) : undefined;
      res.body.dateRetourSt = res.body.dateRetourSt ? moment(res.body.dateRetourSt) : undefined;
      res.body.dateNotification = res.body.dateNotification ? moment(res.body.dateNotification) : undefined;
      res.body.dateSignature = res.body.dateSignature ? moment(res.body.dateSignature) : undefined;
      res.body.taxesTotale = res.body.taxesTotale ? moment(res.body.taxesTotale) : undefined;
      res.body.dateConstatation = res.body.dateConstatation ? moment(res.body.dateConstatation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dossierApfr: IDossierApfr) => {
        dossierApfr.dateReceptionCfv = dossierApfr.dateReceptionCfv ? moment(dossierApfr.dateReceptionCfv) : undefined;
        dossierApfr.dateRetourCt = dossierApfr.dateRetourCt ? moment(dossierApfr.dateRetourCt) : undefined;
        dossierApfr.dateEnregistrement = dossierApfr.dateEnregistrement ? moment(dossierApfr.dateEnregistrement) : undefined;
        dossierApfr.dateDepotSt = dossierApfr.dateDepotSt ? moment(dossierApfr.dateDepotSt) : undefined;
        dossierApfr.dateRetourSt = dossierApfr.dateRetourSt ? moment(dossierApfr.dateRetourSt) : undefined;
        dossierApfr.dateNotification = dossierApfr.dateNotification ? moment(dossierApfr.dateNotification) : undefined;
        dossierApfr.dateSignature = dossierApfr.dateSignature ? moment(dossierApfr.dateSignature) : undefined;
        dossierApfr.taxesTotale = dossierApfr.taxesTotale ? moment(dossierApfr.taxesTotale) : undefined;
        dossierApfr.dateConstatation = dossierApfr.dateConstatation ? moment(dossierApfr.dateConstatation) : undefined;
      });
    }
    return res;
  }
}
