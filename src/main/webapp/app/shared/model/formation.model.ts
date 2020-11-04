import { ISfr } from 'app/shared/model/sfr.model';

export interface IFormation {
  id?: number;
  annee?: number;
  nbrAgentForme?: number;
  themeFormation?: string;
  dureeTotFormation?: number;
  sfrFormation?: ISfr;
}

export class Formation implements IFormation {
  constructor(
    public id?: number,
    public annee?: number,
    public nbrAgentForme?: number,
    public themeFormation?: string,
    public dureeTotFormation?: number,
    public sfrFormation?: ISfr
  ) {}
}
