export interface IPromoteurs {
  id?: number;
  libellePromoteur?: string;
  contactPromoteur?: string;
}

export class Promoteurs implements IPromoteurs {
  constructor(public id?: number, public libellePromoteur?: string, public contactPromoteur?: string) {}
}
