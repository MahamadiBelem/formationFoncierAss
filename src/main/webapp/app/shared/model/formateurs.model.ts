import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IFormateurs {
  id?: number;
  nomComplet?: string;
  specialite?: string;
  regime?: string;
  centreformations?: ICentreFormation[];
}

export class Formateurs implements IFormateurs {
  constructor(
    public id?: number,
    public nomComplet?: string,
    public specialite?: string,
    public regime?: string,
    public centreformations?: ICentreFormation[]
  ) {}
}
