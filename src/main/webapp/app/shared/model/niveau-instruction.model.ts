import { IApprenantes } from 'app/shared/model/apprenantes.model';

export interface INiveauInstruction {
  id?: number;
  niveauInstruction?: string;
  apprenantes?: IApprenantes[];
}

export class NiveauInstruction implements INiveauInstruction {
  constructor(public id?: number, public niveauInstruction?: string, public apprenantes?: IApprenantes[]) {}
}
