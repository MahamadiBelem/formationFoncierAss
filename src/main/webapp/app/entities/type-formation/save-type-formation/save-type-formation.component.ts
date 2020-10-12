import { Component, OnInit } from '@angular/core';
import { TypeFormationService } from '../type-formation.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ITypeFormation, TypeFormation } from 'app/shared/model/type-formation.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-type-formation',
  templateUrl: './save-type-formation.component.html',
  styleUrls: ['./save-type-formation.component.scss'],
})
export class SaveTypeFormationComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleTypeFormation: [],
  });

  constructor(
    protected typeFormationService: TypeFormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeFormation }) => {
      this.updateForm(typeFormation);
    });
  }

  updateForm(typeFormation: ITypeFormation): void {
    this.editForm.patchValue({
      id: typeFormation.id,
      libelleTypeFormation: typeFormation.libelleTypeFormation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeFormation = this.createFromForm();
    this.subscribeToSaveResponse(this.typeFormationService.create(typeFormation));
  }

  private createFromForm(): ITypeFormation {
    return {
      ...new TypeFormation(),
      id: this.editForm.get(['id'])!.value,
      libelleTypeFormation: this.editForm.get(['libelleTypeFormation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeFormation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('typeFormationListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
