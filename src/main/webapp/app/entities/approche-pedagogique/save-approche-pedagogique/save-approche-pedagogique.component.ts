import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ApprochePedagogiqueService } from '../approche-pedagogique.service';
import { ActivatedRoute } from '@angular/router';
import { IApprochePedagogique, ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-approche-pedagogique',
  templateUrl: './save-approche-pedagogique.component.html',
  styleUrls: ['./save-approche-pedagogique.component.scss'],
})
export class SaveApprochePedagogiqueComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleApproche: [null, [Validators.required]],
  });

  constructor(
    protected approchePedagogiqueService: ApprochePedagogiqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ approchePedagogique }) => {
      this.updateForm(approchePedagogique);
    });
  }

  updateForm(approchePedagogique: IApprochePedagogique): void {
    this.editForm.patchValue({
      id: approchePedagogique.id,
      libelleApproche: approchePedagogique.libelleApproche,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const approchePedagogique = this.createFromForm();
    this.subscribeToSaveResponse(this.approchePedagogiqueService.create(approchePedagogique));
  }

  private createFromForm(): IApprochePedagogique {
    return {
      ...new ApprochePedagogique(),
      id: this.editForm.get(['id'])!.value,
      libelleApproche: this.editForm.get(['libelleApproche'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApprochePedagogique>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('approchePedagogiqueListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
