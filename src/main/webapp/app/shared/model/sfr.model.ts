import { IPersonnel } from 'app/shared/model/personnel.model';
import { ICaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';
import { IDossierApfr } from 'app/shared/model/dossier-apfr.model';
import { IImmaDomaine } from 'app/shared/model/imma-domaine.model';
import { IFormationSFR } from 'app/shared/model/formation-sfr.model';
import { ICommunes } from 'app/shared/model/communes.model';
import { IStructureLocale } from 'app/shared/model/structure-locale.model';

export interface ISfr {
  id?: number;
  nbrPersonne?: number;
  personnel?: IPersonnel[];
  caracteristiqueSfrs?: ICaracteristiqueSfr[];
  dossierApfrs?: IDossierApfr[];
  immaDomaines?: IImmaDomaine[];
  formationSFRS?: IFormationSFR[];
  communes?: ICommunes;
  structureLocale?: IStructureLocale;
}

export class Sfr implements ISfr {
  constructor(
    public id?: number,
    public nbrPersonne?: number,
    public personnel?: IPersonnel[],
    public caracteristiqueSfrs?: ICaracteristiqueSfr[],
    public dossierApfrs?: IDossierApfr[],
    public immaDomaines?: IImmaDomaine[],
    public formationSFRS?: IFormationSFR[],
    public communes?: ICommunes,
    public structureLocale?: IStructureLocale
  ) {}
}
