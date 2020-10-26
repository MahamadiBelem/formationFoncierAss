import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegimeService } from '../regime.service';
import { ActivatedRoute } from '@angular/router';
import { IRegime, Regime } from '../../../shared/model/regime.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-regime',
  templateUrl: './save-regime.component.html',
  styleUrls: ['./save-regime.component.scss'],
})
export class SaveRegimeComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleRegime: [null, [Validators.required]],
  });

  constructor(
    protected regimeService: RegimeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regime }) => {
      this.updateForm(regime);
    });
  }

  updateForm(regime: IRegime): void {
    this.editForm.patchValue({
      id: regime.id,
      libelleRegime: regime.libelleRegime,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const regime = this.createFromForm();
    this.subscribeToSaveResponse(this.regimeService.create(regime));

    /* if (regime.id !== undefined) {
      this.subscribeToSaveResponse(this.regimeService.update(regime));
    } else {

    }*/
  }

  private createFromForm(): IRegime {
    return {
      ...new Regime(),
      id: this.editForm.get(['id'])!.value,
      libelleRegime: this.editForm.get(['libelleRegime'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegime>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('regimeListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
