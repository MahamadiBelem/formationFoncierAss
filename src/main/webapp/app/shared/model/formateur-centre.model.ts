import { Moment } from 'moment';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { IFormateurs } from 'app/shared/model/formateurs.model';

export interface IFormateurCentre {
  id?: number;
  dateEtablissement?: Moment;
  specialite?: string;
  regimeFormateur?: string;
  formateurCentreFormation?: ICentreFormation;
  formateurCentre?: IFormateurs;
}

export class FormateurCentre implements IFormateurCentre {
  constructor(
    public id?: number,
    public dateEtablissement?: Moment,
    public specialite?: string,
    public regimeFormateur?: string,
    public formateurCentreFormation?: ICentreFormation,
    public formateurCentre?: IFormateurs
  ) {}
}
