import { Moment } from 'moment';
import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface ISortiePromotion {
  id?: number;
  dateSortie?: Moment;
  anneesSortie?: number;
  motif?: string;
  sortiepromotion?: IApprenantes;
  sortieCentreFormation?: ICentreFormation;
}

export class SortiePromotion implements ISortiePromotion {
  constructor(
    public id?: number,
    public dateSortie?: Moment,
    public anneesSortie?: number,
    public motif?: string,
    public sortiepromotion?: IApprenantes,
    public sortieCentreFormation?: ICentreFormation
  ) {}
}
