import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { PromoteursService } from '../promoteurs.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPromoteurs, Promoteurs } from 'app/shared/model/promoteurs.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-promoteur',
  templateUrl: './update-promoteur.component.html',
  styleUrls: ['./update-promoteur.component.scss'],
})
export class UpdatePromoteurComponent implements OnInit {
  @Input() public promoteurs: any;
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libellePromoteur: [null, [Validators.required]],
    contactPromoteur: [],
  });

  constructor(
    protected promoteursService: PromoteursService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.updateForm(this.promoteurs);
  }

  updateForm(promoteurs: IPromoteurs): void {
    this.editForm.patchValue({
      id: promoteurs.id,
      libellePromoteur: promoteurs.libellePromoteur,
      contactPromoteur: promoteurs.contactPromoteur,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const promoteurs = this.createFromForm();

    if (promoteurs.id !== undefined) {
      this.subscribeToSaveResponse(this.promoteursService.update(promoteurs));
    } else {
      this.subscribeToSaveResponse(this.promoteursService.create(promoteurs));
    }
  }

  private createFromForm(): IPromoteurs {
    return {
      ...new Promoteurs(),
      id: this.editForm.get(['id'])!.value,
      libellePromoteur: this.editForm.get(['libellePromoteur'])!.value,
      contactPromoteur: this.editForm.get(['contactPromoteur'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromoteurs>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('promoteursListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
