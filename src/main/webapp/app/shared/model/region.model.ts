import { IProvinces } from 'app/shared/model/provinces.model';

export interface IRegion {
  id?: number;
  libelleRegion?: string;
  provinces?: IProvinces[];
}

export class Region implements IRegion {
  constructor(public id?: number, public libelleRegion?: string, public provinces?: IProvinces[]) {}
}
