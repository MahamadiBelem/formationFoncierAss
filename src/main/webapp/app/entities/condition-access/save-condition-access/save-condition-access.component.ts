import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ConditionAccessService } from '../condition-access.service';
import { ActivatedRoute } from '@angular/router';
import { IConditionAccess, ConditionAccess } from 'app/shared/model/condition-access.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-condition-access',
  templateUrl: './save-condition-access.component.html',
  styleUrls: ['./save-condition-access.component.scss'],
})
export class SaveConditionAccessComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleConditon: [null, [Validators.required]],
  });

  constructor(
    protected conditionAccessService: ConditionAccessService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conditionAccess }) => {
      this.updateForm(conditionAccess);
    });
  }

  updateForm(conditionAccess: IConditionAccess): void {
    this.editForm.patchValue({
      id: conditionAccess.id,
      libelleConditon: conditionAccess.libelleConditon,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const conditionAccess = this.createFromForm();
    this.subscribeToSaveResponse(this.conditionAccessService.create(conditionAccess));
    /*
    if (conditionAccess.id !== undefined) {
      this.subscribeToSaveResponse(this.conditionAccessService.update(conditionAccess));
    } else {
    
    }
    */
  }

  private createFromForm(): IConditionAccess {
    return {
      ...new ConditionAccess(),
      id: this.editForm.get(['id'])!.value,
      libelleConditon: this.editForm.get(['libelleConditon'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConditionAccess>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('conditionAccessListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodal.dismiss();
  }
}
