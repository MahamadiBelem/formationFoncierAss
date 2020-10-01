import { ICommunes } from 'app/shared/model/communes.model';
import { IRegion } from 'app/shared/model/region.model';

export interface IProvinces {
  id?: number;
  libelleProvince?: string;
  communes?: ICommunes[];
  region?: IRegion;
}

export class Provinces implements IProvinces {
  constructor(public id?: number, public libelleProvince?: string, public communes?: ICommunes[], public region?: IRegion) {}
}
