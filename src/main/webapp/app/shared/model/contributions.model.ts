import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IContributions {
  id?: number;
  libelleContribution?: string;
  centreformations?: ICentreFormation[];
}

export class Contributions implements IContributions {
  constructor(public id?: number, public libelleContribution?: string, public centreformations?: ICentreFormation[]) {}
}
