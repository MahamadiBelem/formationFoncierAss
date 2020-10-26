import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SourceFiancementService } from '../source-fiancement.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ISourceFiancement, SourceFiancement } from '../../../shared/model/source-fiancement.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-source-financement',
  templateUrl: './update-source-financement.component.html',
  styleUrls: ['./update-source-financement.component.scss'],
})
export class UpdateSourceFinancementComponent implements OnInit {
  @Input() public sourceFiancement: any;
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
    this.updateForm(this.sourceFiancement);

    this.activatedRoute.data.subscribe(({ sourceFiancement }) => {});
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

    if (sourceFiancement.id !== undefined) {
      this.subscribeToSaveResponse(this.sourceFiancementService.update(sourceFiancement));
    } else {
      this.subscribeToSaveResponse(this.sourceFiancementService.create(sourceFiancement));
    }
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
