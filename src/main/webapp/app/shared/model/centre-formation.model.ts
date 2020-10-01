import { Moment } from 'moment';
import { IInfrastructure } from 'app/shared/model/infrastructure.model';
import { IAmenagement } from 'app/shared/model/amenagement.model';
import { ICommunes } from 'app/shared/model/communes.model';
import { IPromoteurs } from 'app/shared/model/promoteurs.model';
import { IGestionnaire } from 'app/shared/model/gestionnaire.model';
import { IApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { IPublicCible } from 'app/shared/model/public-cible.model';
import { ISpecialites } from 'app/shared/model/specialites.model';
import { IDomaineFormation } from 'app/shared/model/domaine-formation.model';
import { IContributions } from 'app/shared/model/contributions.model';
import { INiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';
import { IConditionAccess } from 'app/shared/model/condition-access.model';
import { IRegime } from 'app/shared/model/regime.model';

export interface ICentreFormation {
  id?: number;
  denomination?: string;
  localisation?: string;
  adresse?: string;
  statuts?: string;
  capaciteacceuil?: string;
  refOuverture?: string;
  dateOuverture?: Moment;
  infrastructures?: IInfrastructure[];
  amenagements?: IAmenagement[];
  communes?: ICommunes;
  promoteurs?: IPromoteurs;
  gestionnaires?: IGestionnaire;
  approchepedagogiques?: IApprochePedagogique[];
  publiccibles?: IPublicCible[];
  specialites?: ISpecialites[];
  domaineformations?: IDomaineFormation[];
  contributions?: IContributions[];
  niveaurecrutements?: INiveauRecrutement[];
  conditionaccesses?: IConditionAccess[];
  regime?: IRegime;
}

export class CentreFormation implements ICentreFormation {
  constructor(
    public id?: number,
    public denomination?: string,
    public localisation?: string,
    public adresse?: string,
    public statuts?: string,
    public capaciteacceuil?: string,
    public refOuverture?: string,
    public dateOuverture?: Moment,
    public infrastructures?: IInfrastructure[],
    public amenagements?: IAmenagement[],
    public communes?: ICommunes,
    public promoteurs?: IPromoteurs,
    public gestionnaires?: IGestionnaire,
    public approchepedagogiques?: IApprochePedagogique[],
    public publiccibles?: IPublicCible[],
    public specialites?: ISpecialites[],
    public domaineformations?: IDomaineFormation[],
    public contributions?: IContributions[],
    public niveaurecrutements?: INiveauRecrutement[],
    public conditionaccesses?: IConditionAccess[],
    public regime?: IRegime
  ) {}
}
