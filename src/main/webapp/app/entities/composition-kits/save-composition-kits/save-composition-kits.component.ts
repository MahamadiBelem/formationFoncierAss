import { Component, OnInit } from '@angular/core';
import { CompositionKitsService } from '../composition-kits.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { CompositionKits, ICompositionKits } from '../../../shared/model/composition-kits.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-composition-kits',
  templateUrl: './save-composition-kits.component.html',
  styleUrls: ['./save-composition-kits.component.scss'],
})
export class SaveCompositionKitsComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleKits: [],
    quantiteKits: [],
  });

  constructor(
    protected compositionKitsService: CompositionKitsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compositionKits }) => {
      this.updateForm(compositionKits);
    });
  }

  updateForm(compositionKits: ICompositionKits): void {
    this.editForm.patchValue({
      id: compositionKits.id,
      libelleKits: compositionKits.libelleKits,
      quantiteKits: compositionKits.quantiteKits,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compositionKits = this.createFromForm();
    this.subscribeToSaveResponse(this.compositionKitsService.create(compositionKits));

    /*  if (compositionKits.id !== undefined) {
      this.subscribeToSaveResponse(this.compositionKitsService.update(compositionKits));
    } else {

    }*/
  }

  private createFromForm(): ICompositionKits {
    return {
      ...new CompositionKits(),
      id: this.editForm.get(['id'])!.value,
      libelleKits: this.editForm.get(['libelleKits'])!.value,
      quantiteKits: this.editForm.get(['quantiteKits'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompositionKits>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('compositionKitsListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
  cancel(): void {
    this.activemodale.dismiss();
  }
}
