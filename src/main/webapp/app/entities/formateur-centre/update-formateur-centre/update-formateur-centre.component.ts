import { Component, Input, OnInit } from '@angular/core';
import { ICentreFormation } from '../../../shared/model/centre-formation.model';
import { IFormateurs } from '../../../shared/model/formateurs.model';
import { FormBuilder, Validators } from '@angular/forms';
import { FormateurCentreService } from '../formateur-centre.service';
import { CentreFormationService } from '../../centre-formation/centre-formation.service';
import { FormateursService } from '../../formateurs/formateurs.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';
import { FormateurCentre, IFormateurCentre } from '../../../shared/model/formateur-centre.model';
import { Observable } from 'rxjs';
type SelectableEntity = ICentreFormation | IFormateurs;
@Component({
  selector: 'jhi-update-formateur-centre',
  templateUrl: './update-formateur-centre.component.html',
  styleUrls: ['./update-formateur-centre.component.scss'],
})
export class UpdateFormateurCentreComponent implements OnInit {
  @Input() public formateurCentre: any;

  isSaving = false;
  centreformations: ICentreFormation[] = [];
  formateurs: IFormateurs[] = [];
  dateEtablissementDp: any;

  editForm = this.fb.group({
    id: [],
    dateEtablissement: [],
    regimeFormateur: [null, [Validators.required]],
    formateurCentreFormation: [],
    formateurCentre: [],
  });

  constructor(
    protected formateurCentreService: FormateurCentreService,
    protected centreFormationService: CentreFormationService,
    protected formateursService: FormateursService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.updateForm(this.formateurCentre);

    this.activatedRoute.data.subscribe(({ formateurCentre }) => {
      this.centreFormationService.query().subscribe((res: HttpResponse<ICentreFormation[]>) => (this.centreformations = res.body || []));
      this.formateursService.query().subscribe((res: HttpResponse<IFormateurs[]>) => (this.formateurs = res.body || []));
    });
  }

  updateForm(formateurCentre: IFormateurCentre): void {
    this.editForm.patchValue({
      id: formateurCentre.id,
      dateEtablissement: formateurCentre.dateEtablissement,
      regimeFormateur: formateurCentre.regimeFormateur,
      formateurCentreFormation: formateurCentre.formateurCentreFormation,
      formateurCentre: formateurCentre.formateurCentre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formateurCentre = this.createFromForm();

    if (formateurCentre.id !== undefined) {
      this.subscribeToSaveResponse(this.formateurCentreService.update(formateurCentre));
    } else {
      this.subscribeToSaveResponse(this.formateurCentreService.create(formateurCentre));
    }
  }

  private createFromForm(): IFormateurCentre {
    return {
      ...new FormateurCentre(),
      id: this.editForm.get(['id'])!.value,
      dateEtablissement: this.editForm.get(['dateEtablissement'])!.value,
      regimeFormateur: this.editForm.get(['regimeFormateur'])!.value,
      formateurCentreFormation: this.editForm.get(['formateurCentreFormation'])!.value,
      formateurCentre: this.editForm.get(['formateurCentre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormateurCentre>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('formateurCentreListModification');
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
