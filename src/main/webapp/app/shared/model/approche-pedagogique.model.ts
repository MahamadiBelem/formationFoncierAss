import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IApprochePedagogique {
  id?: number;
  libelleApproche?: string;
  centreformations?: ICentreFormation[];
}

export class ApprochePedagogique implements IApprochePedagogique {
  constructor(public id?: number, public libelleApproche?: string, public centreformations?: ICentreFormation[]) {}
}
