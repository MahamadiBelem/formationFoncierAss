import { IPersonnel } from 'app/shared/model/personnel.model';

export interface IProfilPersonnel {
  id?: number;
  libelleProfil?: string;
  personnel?: IPersonnel[];
}

export class ProfilPersonnel implements IProfilPersonnel {
  constructor(public id?: number, public libelleProfil?: string, public personnel?: IPersonnel[]) {}
}
