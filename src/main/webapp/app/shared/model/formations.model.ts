import { ITypeFormation } from 'app/shared/model/type-formation.model';

export interface IFormations {
  id?: number;
  theme?: string;
  lebelleFormation?: string;
  coutFormation?: string;
  sourceFinancement?: string;
  formationTypeFormation?: ITypeFormation;
}

export class Formations implements IFormations {
  constructor(
    public id?: number,
    public theme?: string,
    public lebelleFormation?: string,
    public coutFormation?: string,
    public sourceFinancement?: string,
    public formationTypeFormation?: ITypeFormation
  ) {}
}
