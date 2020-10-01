export interface ITypecandidat {
  id?: number;
  libelleTypeCandidat?: string;
}

export class Typecandidat implements ITypecandidat {
  constructor(public id?: number, public libelleTypeCandidat?: string) {}
}
