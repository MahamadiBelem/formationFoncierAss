import { Moment } from 'moment';
import { ISfr } from 'app/shared/model/sfr.model';
import { ITypeDemandeur } from 'app/shared/model/type-demandeur.model';
import { ITypeActe } from 'app/shared/model/type-acte.model';
import { TrancheAge } from 'app/shared/model/enumerations/tranche-age.model';

export interface IDossierApfr {
  id?: number;
  numApfr?: string;
  trancheAge?: TrancheAge;
  dateReceptionCfv?: Moment;
  dateRetourCt?: Moment;
  dateEnregistrement?: Moment;
  dateDepotSt?: Moment;
  dateRetourSt?: Moment;
  dateNotification?: Moment;
  dateSignature?: Moment;
  taxesTotale?: Moment;
  donCommune?: number;
  superficieSecurise?: number;
  dateConstatation?: Moment;
  sfrDossierApfr?: ISfr;
  dossierApfrTypeDemandeur?: ITypeDemandeur;
  typeDossierApfr?: ITypeActe;
}

export class DossierApfr implements IDossierApfr {
  constructor(
    public id?: number,
    public numApfr?: string,
    public trancheAge?: TrancheAge,
    public dateReceptionCfv?: Moment,
    public dateRetourCt?: Moment,
    public dateEnregistrement?: Moment,
    public dateDepotSt?: Moment,
    public dateRetourSt?: Moment,
    public dateNotification?: Moment,
    public dateSignature?: Moment,
    public taxesTotale?: Moment,
    public donCommune?: number,
    public superficieSecurise?: number,
    public dateConstatation?: Moment,
    public sfrDossierApfr?: ISfr,
    public dossierApfrTypeDemandeur?: ITypeDemandeur,
    public typeDossierApfr?: ITypeActe
  ) {}
}
