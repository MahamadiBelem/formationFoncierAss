import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface INiveauRecrutement {
  id?: number;
  libelleNiveau?: string;
  centreformations?: ICentreFormation[];
}

export class NiveauRecrutement implements INiveauRecrutement {
  constructor(public id?: number, public libelleNiveau?: string, public centreformations?: ICentreFormation[]) {}
}
