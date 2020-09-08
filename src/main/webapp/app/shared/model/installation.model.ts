import { Moment } from 'moment';
import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { IKits } from 'app/shared/model/kits.model';

export interface IInstallation {
  id?: number;
  anneesInstallation?: string;
  dateIntallation?: Moment;
  lieuInstallation?: string;
  activiteinstallations?: IActiviteInstallation[];
  kits?: IKits[];
}

export class Installation implements IInstallation {
  constructor(
    public id?: number,
    public anneesInstallation?: string,
    public dateIntallation?: Moment,
    public lieuInstallation?: string,
    public activiteinstallations?: IActiviteInstallation[],
    public kits?: IKits[]
  ) {}
}
