export interface IProvinces {
  id?: number;
  libelleProvince?: string;
}

export class Provinces implements IProvinces {
  constructor(public id?: number, public libelleProvince?: string) {}
}
