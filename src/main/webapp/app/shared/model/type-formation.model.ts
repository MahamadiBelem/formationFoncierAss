export interface ITypeFormation {
  id?: number;
  libelleTypeFormation?: string;
}

export class TypeFormation implements ITypeFormation {
  constructor(public id?: number, public libelleTypeFormation?: string) {}
}
