import { Moment } from 'moment';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { IFormateurs } from 'app/shared/model/formateurs.model';
import { IRegime } from 'app/shared/model/regime.model';
import { RegimeFormateur } from 'app/shared/model/enumerations/regime-formateur.model';

export interface IFormateurCentre {
  id?: number;
  dateEtablissement?: Moment;
  regimeFormateur?: RegimeFormateur;
  formateurCentreFormation?: ICentreFormation;
  formateurCentre?: IFormateurs;
  regimecentreformateur?: IRegime;
}

export class FormateurCentre implements IFormateurCentre {
  constructor(
    public id?: number,
    public dateEtablissement?: Moment,
    public regimeFormateur?: RegimeFormateur,
    public formateurCentreFormation?: ICentreFormation,
    public formateurCentre?: IFormateurs,
    public regimecentreformateur?: IRegime
  ) {}
}
