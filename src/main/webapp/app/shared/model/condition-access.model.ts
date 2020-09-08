import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IConditionAccess {
  id?: number;
  libelleConditon?: string;
  centreformations?: ICentreFormation[];
}

export class ConditionAccess implements IConditionAccess {
  constructor(public id?: number, public libelleConditon?: string, public centreformations?: ICentreFormation[]) {}
}
