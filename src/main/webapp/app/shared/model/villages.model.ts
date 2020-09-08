export interface IVillages {
  id?: number;
  libelleVillage?: string;
}

export class Villages implements IVillages {
  constructor(public id?: number, public libelleVillage?: string) {}
}
