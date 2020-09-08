import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IPublicCible {
  id?: number;
  libellePublic?: string;
  centreformations?: ICentreFormation[];
}

export class PublicCible implements IPublicCible {
  constructor(public id?: number, public libellePublic?: string, public centreformations?: ICentreFormation[]) {}
}
