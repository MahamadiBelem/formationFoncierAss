import { ISfr } from 'app/shared/model/sfr.model';
import { IVillages } from 'app/shared/model/villages.model';
import { IProvinces } from 'app/shared/model/provinces.model';

export interface ICommunes {
  id?: number;
  libelleCommune?: string;
  sfr?: ISfr;
  villages?: IVillages[];
  provinces?: IProvinces;
}

export class Communes implements ICommunes {
  constructor(
    public id?: number,
    public libelleCommune?: string,
    public sfr?: ISfr,
    public villages?: IVillages[],
    public provinces?: IProvinces
  ) {}
}
