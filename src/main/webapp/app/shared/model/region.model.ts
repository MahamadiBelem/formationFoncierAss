export interface IRegion {
  id?: number;
  libelleRegion?: string;
}

export class Region implements IRegion {
  constructor(public id?: number, public libelleRegion?: string) {}
}
