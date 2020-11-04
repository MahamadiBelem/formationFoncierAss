import { ISfr } from 'app/shared/model/sfr.model';
import { IProfilPersonnel } from 'app/shared/model/profil-personnel.model';

export interface IPersonnel {
  id?: number;
  nomPers?: string;
  prenomPers?: string;
  telPers?: string;
  emailPers?: string;
  sfrPersonnel?: ISfr;
  personnel?: IProfilPersonnel;
}

export class Personnel implements IPersonnel {
  constructor(
    public id?: number,
    public nomPers?: string,
    public prenomPers?: string,
    public telPers?: string,
    public emailPers?: string,
    public sfrPersonnel?: ISfr,
    public personnel?: IProfilPersonnel
  ) {}
}
