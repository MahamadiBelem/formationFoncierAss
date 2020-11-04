import { ISfr } from 'app/shared/model/sfr.model';

export interface ICaracteristiqueSfr {
  id?: number;
  annee?: number;
  nbrAgent?: number;
  equipementInformatique?: boolean;
  equipementCartographique?: boolean;
  accesInternet?: boolean;
  equipemementVehicule?: boolean;
  caracteristiqueSfr?: ISfr;
}

export class CaracteristiqueSfr implements ICaracteristiqueSfr {
  constructor(
    public id?: number,
    public annee?: number,
    public nbrAgent?: number,
    public equipementInformatique?: boolean,
    public equipementCartographique?: boolean,
    public accesInternet?: boolean,
    public equipemementVehicule?: boolean,
    public caracteristiqueSfr?: ISfr
  ) {
    this.equipementInformatique = this.equipementInformatique || false;
    this.equipementCartographique = this.equipementCartographique || false;
    this.accesInternet = this.accesInternet || false;
    this.equipemementVehicule = this.equipemementVehicule || false;
  }
}
