import { IDossierApfr } from 'app/shared/model/dossier-apfr.model';

export interface ITypeDemandeur {
  id?: number;
  libelleTypeDemandeur?: string;
  dossierApfrs?: IDossierApfr[];
}

export class TypeDemandeur implements ITypeDemandeur {
  constructor(public id?: number, public libelleTypeDemandeur?: string, public dossierApfrs?: IDossierApfr[]) {}
}
