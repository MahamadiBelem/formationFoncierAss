import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { ContributionsService } from '../contributions.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IContributions, Contributions } from 'app/shared/model/contributions.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-contribution',
  templateUrl: './update-contribution.component.html',
  styleUrls: ['./update-contribution.component.scss'],
})
export class UpdateContributionComponent implements OnInit {
  @Input() public contributions: any;
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleContribution: [null, [Validators.required]],
  });

  constructor(
    protected contributionsService: ContributionsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.updateForm(this.contributions);
  }

  updateForm(contributions: IContributions): void {
    this.editForm.patchValue({
      id: contributions.id,
      libelleContribution: contributions.libelleContribution,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contributions = this.createFromForm();

    if (contributions.id !== undefined) {
      this.subscribeToSaveResponse(this.contributionsService.update(contributions));
    } else {
      this.subscribeToSaveResponse(this.contributionsService.create(contributions));
    }
  }

  private createFromForm(): IContributions {
    return {
      ...new Contributions(),
      id: this.editForm.get(['id'])!.value,
      libelleContribution: this.editForm.get(['libelleContribution'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContributions>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('contributionsListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
  cancel(): void {
    this.activemodale.dismiss();
  }
}
