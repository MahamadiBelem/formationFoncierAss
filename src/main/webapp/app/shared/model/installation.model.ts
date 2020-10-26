import { Moment } from 'moment';
import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ISourceFiancement } from 'app/shared/model/source-fiancement.model';

export interface IInstallation {
  id?: number;
  anneesInstallation?: string;
  dateIntallation?: Moment;
  lieuInstallation?: string;
  iskit?: boolean;
  installation?: ISortiePromotion;
  activiteinstallations?: IActiviteInstallation[];
  sourceIntallations?: ISourceFiancement[];
}

export class Installation implements IInstallation {
  constructor(
    public id?: number,
    public anneesInstallation?: string,
    public dateIntallation?: Moment,
    public lieuInstallation?: string,
    public iskit?: boolean,
    public installation?: ISortiePromotion,
    public activiteinstallations?: IActiviteInstallation[],
    public sourceIntallations?: ISourceFiancement[]
  ) {
    this.iskit = this.iskit || false;
  }
}
