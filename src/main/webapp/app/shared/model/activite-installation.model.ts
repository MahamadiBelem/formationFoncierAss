import { ICompositionKits } from 'app/shared/model/composition-kits.model';
import { IInstallation } from 'app/shared/model/installation.model';

export interface IActiviteInstallation {
  id?: number;
  libelleActivite?: string;
  kits?: ICompositionKits[];
  installations?: IInstallation[];
}

export class ActiviteInstallation implements IActiviteInstallation {
  constructor(
    public id?: number,
    public libelleActivite?: string,
    public kits?: ICompositionKits[],
    public installations?: IInstallation[]
  ) {}
}
