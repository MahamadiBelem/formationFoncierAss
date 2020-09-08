import { IKits } from 'app/shared/model/kits.model';

export interface ICompositionKits {
  id?: number;
  libelleKits?: string;
  quantiteKits?: number;
  kits?: IKits[];
}

export class CompositionKits implements ICompositionKits {
  constructor(public id?: number, public libelleKits?: string, public quantiteKits?: number, public kits?: IKits[]) {}
}
