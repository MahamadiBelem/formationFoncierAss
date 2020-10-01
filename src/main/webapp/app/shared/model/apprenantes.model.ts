import { Moment } from 'moment';
import { INiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';
import { Examen } from 'app/shared/model/enumerations/examen.model';

export interface IApprenantes {
  id?: number;
  matricule?: string;
  nom?: string;
  prenom?: string;
  dateNaissance?: Moment;
  sexe?: Sexe;
  contact?: string;
  typecandidat?: Examen;
  situationMatrimonial?: string;
  charger?: string;
  localite?: string;
  niveauapprenant?: INiveauInstruction;
}

export class Apprenantes implements IApprenantes {
  constructor(
    public id?: number,
    public matricule?: string,
    public nom?: string,
    public prenom?: string,
    public dateNaissance?: Moment,
    public sexe?: Sexe,
    public contact?: string,
    public typecandidat?: Examen,
    public situationMatrimonial?: string,
    public charger?: string,
    public localite?: string,
    public niveauapprenant?: INiveauInstruction
  ) {}
}
