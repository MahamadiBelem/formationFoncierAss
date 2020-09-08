import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface ISpecialites {
  id?: number;
  libelleSpecialite?: string;
  centreformations?: ICentreFormation[];
}

export class Specialites implements ISpecialites {
  constructor(public id?: number, public libelleSpecialite?: string, public centreformations?: ICentreFormation[]) {}
}
