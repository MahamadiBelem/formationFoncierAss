import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegimeService } from '../regime.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRegime, Regime } from '../../../shared/model/regime.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-upate-regime',
  templateUrl: './upate-regime.component.html',
  styleUrls: ['./upate-regime.component.scss'],
})
export class UpateRegimeComponent implements OnInit {
  isSaving = false;
  @Input() public regime: any;
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
    this.updateForm(this.regime);

    /* this.activatedRoute.data.subscribe(({ regime }) => {

    });*/
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

    if (regime.id !== undefined) {
      this.subscribeToSaveResponse(this.regimeService.update(regime));
    } else {
      this.subscribeToSaveResponse(this.regimeService.create(regime));
    }
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
