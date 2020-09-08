import { ITypeAmenagement } from 'app/shared/model/type-amenagement.model';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IAmenagement {
  id?: number;
  superficieTotal?: number;
  superficieEmbave?: number;
  etats?: string;
  typeamenagement?: ITypeAmenagement;
  centreformation?: ICentreFormation;
}

export class Amenagement implements IAmenagement {
  constructor(
    public id?: number,
    public superficieTotal?: number,
    public superficieEmbave?: number,
    public etats?: string,
    public typeamenagement?: ITypeAmenagement,
    public centreformation?: ICentreFormation
  ) {}
}
