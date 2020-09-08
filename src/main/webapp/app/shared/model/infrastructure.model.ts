import { Moment } from 'moment';
import { ITypeInfratructure } from 'app/shared/model/type-infratructure.model';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IInfrastructure {
  id?: number;
  dateElaboration?: Moment;
  fonctionnalite?: string;
  etat?: string;
  typeinfrastructure?: ITypeInfratructure;
  centreformation?: ICentreFormation;
}

export class Infrastructure implements IInfrastructure {
  constructor(
    public id?: number,
    public dateElaboration?: Moment,
    public fonctionnalite?: string,
    public etat?: string,
    public typeinfrastructure?: ITypeInfratructure,
    public centreformation?: ICentreFormation
  ) {}
}
