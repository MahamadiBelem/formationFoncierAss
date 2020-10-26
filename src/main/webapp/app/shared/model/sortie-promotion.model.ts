import { Moment } from 'moment';
import { IInscription } from 'app/shared/model/inscription.model';

export interface ISortiePromotion {
  id?: number;
  dateSortie?: Moment;
  anneesSortie?: number;
  motif?: string;
  issortie?: boolean;
  sortiepromotion?: IInscription;
}

export class SortiePromotion implements ISortiePromotion {
  constructor(
    public id?: number,
    public dateSortie?: Moment,
    public anneesSortie?: number,
    public motif?: string,
    public issortie?: boolean,
    public sortiepromotion?: IInscription
  ) {
    this.issortie = this.issortie || false;
  }
}
