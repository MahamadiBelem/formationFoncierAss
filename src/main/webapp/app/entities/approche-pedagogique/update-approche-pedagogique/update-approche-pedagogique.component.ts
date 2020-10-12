import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ApprochePedagogiqueService } from '../approche-pedagogique.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IApprochePedagogique, ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-approche-pedagogique',
  templateUrl: './update-approche-pedagogique.component.html',
  styleUrls: ['./update-approche-pedagogique.component.scss'],
})
export class UpdateApprochePedagogiqueComponent implements OnInit {
  isSaving = false;
  @Input() public approchePedagogique: any;
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
    this.updateForm(this.approchePedagogique);
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
    if (approchePedagogique.id !== undefined) {
      this.subscribeToSaveResponse(this.approchePedagogiqueService.update(approchePedagogique));
    } else {
      this.subscribeToSaveResponse(this.approchePedagogiqueService.create(approchePedagogique));
    }
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
