import { Moment } from 'moment';
import { ICentreFormation } from 'app/shared/model/centre-formation.model';
import { IFormations } from 'app/shared/model/formations.model';

export interface IFormationCentreFormation {
  id?: number;
  datedebut?: Moment;
  dateClore?: Moment;
  formationcentre?: ICentreFormation;
  centreformation?: IFormations;
}

export class FormationCentreFormation implements IFormationCentreFormation {
  constructor(
    public id?: number,
    public datedebut?: Moment,
    public dateClore?: Moment,
    public formationcentre?: ICentreFormation,
    public centreformation?: IFormations
  ) {}
}
