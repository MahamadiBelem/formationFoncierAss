import { IInstallation } from 'app/shared/model/installation.model';
import { ICompositionKits } from 'app/shared/model/composition-kits.model';

export interface IKits {
  id?: number;
  isobtenu?: boolean;
  installationKits?: IInstallation[];
  installations?: ICompositionKits[];
}

export class Kits implements IKits {
  constructor(
    public id?: number,
    public isobtenu?: boolean,
    public installationKits?: IInstallation[],
    public installations?: ICompositionKits[]
  ) {
    this.isobtenu = this.isobtenu || false;
  }
}
