import { ICommunes } from 'app/shared/model/communes.model';

export interface IVillages {
  id?: number;
  libelleVillage?: string;
  commune?: ICommunes;
}

export class Villages implements IVillages {
  constructor(public id?: number, public libelleVillage?: string, public commune?: ICommunes) {}
}
