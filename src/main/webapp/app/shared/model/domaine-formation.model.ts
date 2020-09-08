import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IDomaineFormation {
  id?: number;
  libelleDomaine?: string;
  centreformations?: ICentreFormation[];
}

export class DomaineFormation implements IDomaineFormation {
  constructor(public id?: number, public libelleDomaine?: string, public centreformations?: ICentreFormation[]) {}
}
