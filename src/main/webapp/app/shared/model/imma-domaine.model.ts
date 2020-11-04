import { ISfr } from 'app/shared/model/sfr.model';

export interface IImmaDomaine {
  id?: number;
  annee?: number;
  superficeTotInventorie?: number;
  superficieTotImmatricule?: number;
  superficieTotEncadre?: number;
  sfrImmaDomaine?: ISfr;
}

export class ImmaDomaine implements IImmaDomaine {
  constructor(
    public id?: number,
    public annee?: number,
    public superficeTotInventorie?: number,
    public superficieTotImmatricule?: number,
    public superficieTotEncadre?: number,
    public sfrImmaDomaine?: ISfr
  ) {}
}
