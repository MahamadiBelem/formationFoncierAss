import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';

type EntityResponseType = HttpResponse<ISortiePromotion>;
type EntityArrayResponseType = HttpResponse<ISortiePromotion[]>;

@Injectable({ providedIn: 'root' })
export class SortiePromotionService {
  public resourceUrl = SERVER_API_URL + 'api/sortie-promotions';
  public externatresource = SERVER_API_URL + 'api/sortie-promotion-issortie';
  constructor(protected http: HttpClient) {}

  create(sortiePromotion: ISortiePromotion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sortiePromotion);
    return this.http
      .post<ISortiePromotion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sortiePromotion: ISortiePromotion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sortiePromotion);
    return this.http
      .put<ISortiePromotion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISortiePromotion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISortiePromotion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  findisSortie(): Observable<EntityArrayResponseType> {
    return this.http
      .get<ISortiePromotion[]>(this.externatresource, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sortiePromotion: ISortiePromotion): ISortiePromotion {
    const copy: ISortiePromotion = Object.assign({}, sortiePromotion, {
      dateSortie:
        sortiePromotion.dateSortie && sortiePromotion.dateSortie.isValid() ? sortiePromotion.dateSortie.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateSortie = res.body.dateSortie ? moment(res.body.dateSortie) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sortiePromotion: ISortiePromotion) => {
        sortiePromotion.dateSortie = sortiePromotion.dateSortie ? moment(sortiePromotion.dateSortie) : undefined;
      });
    }
    return res;
  }
}
