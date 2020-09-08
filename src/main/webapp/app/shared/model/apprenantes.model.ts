import { Moment } from 'moment';
import { ISortiePromotion } from 'app/shared/model/sortie-promotion.model';
import { IFormations } from 'app/shared/model/formations.model';

export interface IApprenantes {
  id?: number;
  matricule?: string;
  nom?: string;
  prenom?: string;
  dateNaissance?: Moment;
  sexe?: string;
  contact?: string;
  iscandidat?: boolean;
  situationMatrimonial?: string;
  charger?: string;
  localite?: string;
  sortiepromotion?: ISortiePromotion;
  centreformations?: IFormations[];
}

export class Apprenantes implements IApprenantes {
  constructor(
    public id?: number,
    public matricule?: string,
    public nom?: string,
    public prenom?: string,
    public dateNaissance?: Moment,
    public sexe?: string,
    public contact?: string,
    public iscandidat?: boolean,
    public situationMatrimonial?: string,
    public charger?: string,
    public localite?: string,
    public sortiepromotion?: ISortiePromotion,
    public centreformations?: IFormations[]
  ) {
    this.iscandidat = this.iscandidat || false;
  }
}
