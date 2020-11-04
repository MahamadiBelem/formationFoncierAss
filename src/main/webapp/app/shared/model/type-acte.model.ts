import { IDossierApfr } from 'app/shared/model/dossier-apfr.model';

export interface ITypeActe {
  id?: number;
  libelleTypeActe?: string;
  dossierApfrs?: IDossierApfr[];
}

export class TypeActe implements ITypeActe {
  constructor(public id?: number, public libelleTypeActe?: string, public dossierApfrs?: IDossierApfr[]) {}
}
