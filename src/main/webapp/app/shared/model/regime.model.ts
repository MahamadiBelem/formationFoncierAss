import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { IFormateurCentre } from 'app/shared/model/formateur-centre.model';

export interface IRegime {
  id?: number;
  libelleRegime?: string;
  centreFormations?: ICentreFormation[];
  formateurCentres?: IFormateurCentre[];
}

export class Regime implements IRegime {
  constructor(
    public id?: number,
    public libelleRegime?: string,
    public centreFormations?: ICentreFormation[],
    public formateurCentres?: IFormateurCentre[]
  ) {}
}
