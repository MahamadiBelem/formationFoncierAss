export interface IKits {
  id?: number;
  isobtenu?: boolean;
}

export class Kits implements IKits {
  constructor(public id?: number, public isobtenu?: boolean) {
    this.isobtenu = this.isobtenu || false;
  }
}
