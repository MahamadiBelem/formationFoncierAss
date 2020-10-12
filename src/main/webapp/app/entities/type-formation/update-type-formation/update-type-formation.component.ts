import { Component, OnInit, Input } from '@angular/core';
import { TypeFormationService } from '../type-formation.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITypeFormation, TypeFormation } from 'app/shared/model/type-formation.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-type-formation',
  templateUrl: './update-type-formation.component.html',
  styleUrls: ['./update-type-formation.component.scss'],
})
export class UpdateTypeFormationComponent implements OnInit {
  @Input() public typeFormation: any;
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
    this.updateForm(this.typeFormation);
    /*
    this.activatedRoute.data.subscribe(({ typeFormation }) => {
     
    });
    */
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
    if (typeFormation.id !== undefined) {
      this.subscribeToSaveResponse(this.typeFormationService.update(typeFormation));
    } else {
      this.subscribeToSaveResponse(this.typeFormationService.create(typeFormation));
    }
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
