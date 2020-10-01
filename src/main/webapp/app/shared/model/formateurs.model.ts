export interface IFormateurs {
  id?: number;
  nomComplet?: string;
  emplois?: string;
  contactformateur?: string;
}

export class Formateurs implements IFormateurs {
  constructor(public id?: number, public nomComplet?: string, public emplois?: string, public contactformateur?: string) {}
}
