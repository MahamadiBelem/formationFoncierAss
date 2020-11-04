import { ISfr } from 'app/shared/model/sfr.model';

export interface IFormationSFR {
  id?: number;
  annee?: number;
  nbrAgentForme?: number;
  themeFormation?: string;
  dureeTotFormation?: number;
  sfrFormation?: ISfr;
}

export class FormationSFR implements IFormationSFR {
  constructor(
    public id?: number,
    public annee?: number,
    public nbrAgentForme?: number,
    public themeFormation?: string,
    public dureeTotFormation?: number,
    public sfrFormation?: ISfr
  ) {}
}
