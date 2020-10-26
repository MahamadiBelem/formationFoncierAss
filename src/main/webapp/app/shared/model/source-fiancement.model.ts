import { IInstallation } from 'app/shared/model/installation.model';
import { Partenaire } from 'app/shared/model/enumerations/partenaire.model';

export interface ISourceFiancement {
  id?: number;
  libelleSource?: string;
  partenaire?: Partenaire;
  installationSources?: IInstallation[];
}

export class SourceFiancement implements ISourceFiancement {
  constructor(
    public id?: number,
    public libelleSource?: string,
    public partenaire?: Partenaire,
    public installationSources?: IInstallation[]
  ) {}
}
