import { IApprenantes } from 'app/shared/model/apprenantes.model';

export interface INiveauInstruction {
  id?: number;
  niveauInstruction?: string;
  apprenants?: IApprenantes;
}

export class NiveauInstruction implements INiveauInstruction {
  constructor(public id?: number, public niveauInstruction?: string, public apprenants?: IApprenantes) {}
}
