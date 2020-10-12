import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { DomaineFormationService } from '../domaine-formation.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IDomaineFormation, DomaineFormation } from 'app/shared/model/domaine-formation.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-domaine-formation',
  templateUrl: './update-domaine-formation.component.html',
  styleUrls: ['./update-domaine-formation.component.scss'],
})
export class UpdateDomaineFormationComponent implements OnInit {
  @Input() public domaineFormation: any;
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleDomaine: [null, [Validators.required]],
  });

  constructor(
    protected domaineFormationService: DomaineFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    alert('salut' + this.domaineFormation.libelleDomaine);
    this.updateForm(this.domaineFormation);
  }

  updateForm(domaineFormation: IDomaineFormation): void {
    this.editForm.patchValue({
      id: domaineFormation.id,
      libelleDomaine: domaineFormation.libelleDomaine,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const domaineFormation = this.createFromForm();
    if (domaineFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.domaineFormationService.update(domaineFormation));
    } else {
      this.subscribeToSaveResponse(this.domaineFormationService.create(domaineFormation));
    }
  }

  private createFromForm(): IDomaineFormation {
    return {
      ...new DomaineFormation(),
      id: this.editForm.get(['id'])!.value,
      libelleDomaine: this.editForm.get(['libelleDomaine'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDomaineFormation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('domaineFormationListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
