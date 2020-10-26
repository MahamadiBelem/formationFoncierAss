import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';

export interface ICompositionKits {
  id?: number;
  libelleKits?: string;
  quantiteKits?: number;
  installationKits?: IActiviteInstallation[];
}

export class CompositionKits implements ICompositionKits {
  constructor(
    public id?: number,
    public libelleKits?: string,
    public quantiteKits?: number,
    public installationKits?: IActiviteInstallation[]
  ) {}
}
