import { IFormations } from 'app/shared/model/formations.model';
import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';

export interface IInscription {
  id?: number;
  anneesAcademique?: string;
  dateInscription?: string;
  inscription?: IFormations;
  inscriptionApprenant?: IApprenantes;
  inscriptionCentreFormation?: ICentreFormation;
}

export class Inscription implements IInscription {
  constructor(
    public id?: number,
    public anneesAcademique?: string,
    public dateInscription?: string,
    public inscription?: IFormations,
    public inscriptionApprenant?: IApprenantes,
    public inscriptionCentreFormation?: ICentreFormation
  ) {}
}
