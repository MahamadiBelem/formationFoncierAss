import { Moment } from 'moment';
import { IInstallation } from 'app/shared/model/installation.model';

export interface ISortiePromotion {
  id?: number;
  dateSortie?: Moment;
  anneesSortie?: number;
  motif?: string;
  installation?: IInstallation;
}

export class SortiePromotion implements ISortiePromotion {
  constructor(
    public id?: number,
    public dateSortie?: Moment,
    public anneesSortie?: number,
    public motif?: string,
    public installation?: IInstallation
  ) {}
}
