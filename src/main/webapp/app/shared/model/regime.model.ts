import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IRegime {
  id?: number;
  libelleRegime?: string;
  centreFormations?: ICentreFormation[];
}

export class Regime implements IRegime {
  constructor(public id?: number, public libelleRegime?: string, public centreFormations?: ICentreFormation[]) {}
}
