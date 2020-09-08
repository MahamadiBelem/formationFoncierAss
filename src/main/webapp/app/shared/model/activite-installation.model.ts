import { IInstallation } from 'app/shared/model/installation.model';

export interface IActiviteInstallation {
  id?: number;
  libelleActivite?: string;
  installations?: IInstallation[];
}

export class ActiviteInstallation implements IActiviteInstallation {
  constructor(public id?: number, public libelleActivite?: string, public installations?: IInstallation[]) {}
}
