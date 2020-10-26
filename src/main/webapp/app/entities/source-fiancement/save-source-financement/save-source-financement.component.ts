import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SourceFiancementService } from '../source-fiancement.service';
import { ActivatedRoute } from '@angular/router';
import { ISourceFiancement, SourceFiancement } from '../../../shared/model/source-fiancement.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-source-financement',
  templateUrl: './save-source-financement.component.html',
  styleUrls: ['./save-source-financement.component.scss'],
})
export class SaveSourceFinancementComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleSource: [null, [Validators.required]],
    partenaire: [],
  });

  constructor(
    protected sourceFiancementService: SourceFiancementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sourceFiancement }) => {
      this.updateForm(sourceFiancement);
    });
  }

  updateForm(sourceFiancement: ISourceFiancement): void {
    this.editForm.patchValue({
      id: sourceFiancement.id,
      libelleSource: sourceFiancement.libelleSource,
      partenaire: sourceFiancement.partenaire,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sourceFiancement = this.createFromForm();
    this.subscribeToSaveResponse(this.sourceFiancementService.create(sourceFiancement));

    /* if (sourceFiancement.id !== undefined) {
      this.subscribeToSaveResponse(this.sourceFiancementService.update(sourceFiancement));
    } else {

    }*/
  }

  private createFromForm(): ISourceFiancement {
    return {
      ...new SourceFiancement(),
      id: this.editForm.get(['id'])!.value,
      libelleSource: this.editForm.get(['libelleSource'])!.value,
      partenaire: this.editForm.get(['partenaire'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISourceFiancement>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('sourceFiancementListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
