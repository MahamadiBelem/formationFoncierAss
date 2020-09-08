import { ITypeFormation } from 'app/shared/model/type-formation.model';
import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IFormations {
  id?: number;
  theme?: string;
  lebelleFormation?: string;
  coutFormation?: string;
  sourceFinancement?: string;
  typeamenagement?: ITypeFormation;
  formations?: IApprenantes[];
  centreformations?: ICentreFormation[];
}

export class Formations implements IFormations {
  constructor(
    public id?: number,
    public theme?: string,
    public lebelleFormation?: string,
    public coutFormation?: string,
    public sourceFinancement?: string,
    public typeamenagement?: ITypeFormation,
    public formations?: IApprenantes[],
    public centreformations?: ICentreFormation[]
  ) {}
}
