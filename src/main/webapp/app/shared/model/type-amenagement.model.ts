export interface ITypeAmenagement {
  id?: number;
  libelleTypeAmenagement?: string;
}

export class TypeAmenagement implements ITypeAmenagement {
  constructor(public id?: number, public libelleTypeAmenagement?: string) {}
}
