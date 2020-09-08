export interface ICommunes {
  id?: number;
  libelleCommune?: string;
}

export class Communes implements ICommunes {
  constructor(public id?: number, public libelleCommune?: string) {}
}
