export interface IGestionnaire {
  id?: number;
  nomComplet?: string;
  contactsGestionnaires?: string;
  emploi?: string;
}

export class Gestionnaire implements IGestionnaire {
  constructor(public id?: number, public nomComplet?: string, public contactsGestionnaires?: string, public emploi?: string) {}
}
