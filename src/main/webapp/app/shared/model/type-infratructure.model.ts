export interface ITypeInfratructure {
  id?: number;
  libelleTypeInfracture?: string;
}

export class TypeInfratructure implements ITypeInfratructure {
  constructor(public id?: number, public libelleTypeInfracture?: string) {}
}
