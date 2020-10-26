import { Component, Input, OnInit } from '@angular/core';
import { ICentreFormation } from '../../../shared/model/centre-formation.model';
import { IFormations } from '../../../shared/model/formations.model';
import { FormationCentreFormationService } from '../formation-centre-formation.service';
import { CentreFormationService } from '../../centre-formation/centre-formation.service';
import { FormationsService } from '../../formations/formations.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';
import { FormationCentreFormation, IFormationCentreFormation } from '../../../shared/model/formation-centre-formation.model';
import { Observable } from 'rxjs';
type SelectableEntity = ICentreFormation | IFormations;
@Component({
  selector: 'jhi-update-formation-centre',
  templateUrl: './update-formation-centre.component.html',
  styleUrls: ['./update-formation-centre.component.scss'],
})
export class UpdateFormationCentreComponent implements OnInit {
  @Input() public formationCentreFormation: any;
  isSaving = false;
  centreformations: ICentreFormation[] = [];
  formations: IFormations[] = [];
  datedebutDp: any;
  dateCloreDp: any;

  editForm = this.fb.group({
    id: [],
    datedebut: [],
    dateClore: [],
    formationcentre: [],
    centreformation: [],
  });

  constructor(
    protected formationCentreFormationService: FormationCentreFormationService,
    protected centreFormationService: CentreFormationService,
    protected formationsService: FormationsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));

    this.formationsService.query().subscribe((res: HttpResponse<IFormations[]>) => (this.formations = res.body || []));
    this.updateForm(this.formationCentreFormation);

    /*this.activatedRoute.data.subscribe(({ formationCentreFormation }) => {


      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));

      this.formationsService.query().subscribe((res: HttpResponse<IFormations[]>) => (this.formations = res.body || []));
    });*/
  }

  updateForm(formationCentreFormation: IFormationCentreFormation): void {
    this.editForm.patchValue({
      id: formationCentreFormation.id,
      datedebut: formationCentreFormation.datedebut,
      dateClore: formationCentreFormation.dateClore,
      formationcentre: formationCentreFormation.formationcentre,
      centreformation: formationCentreFormation.centreformation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formationCentreFormation = this.createFromForm();

    if (formationCentreFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.formationCentreFormationService.update(formationCentreFormation));
    } else {
      this.subscribeToSaveResponse(this.formationCentreFormationService.create(formationCentreFormation));
    }
  }

  private createFromForm(): IFormationCentreFormation {
    return {
      ...new FormationCentreFormation(),
      id: this.editForm.get(['id'])!.value,
      datedebut: this.editForm.get(['datedebut'])!.value,
      dateClore: this.editForm.get(['dateClore'])!.value,
      formationcentre: this.editForm.get(['formationcentre'])!.value,
      centreformation: this.editForm.get(['centreformation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormationCentreFormation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('formationCentreFormationListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
