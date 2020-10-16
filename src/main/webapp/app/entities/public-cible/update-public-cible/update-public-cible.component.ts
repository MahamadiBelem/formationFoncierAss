import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { PublicCibleService } from '../public-cible.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPublicCible, PublicCible } from 'app/shared/model/public-cible.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-public-cible',
  templateUrl: './update-public-cible.component.html',
  styleUrls: ['./update-public-cible.component.scss'],
})
export class UpdatePublicCibleComponent implements OnInit {
  isSaving = false;
  @Input() public publicCible: any;
  editForm = this.fb.group({
    id: [],
    libellePublic: [null, [Validators.required]],
  });

  constructor(
    protected publicCibleService: PublicCibleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.updateForm(this.publicCible);
  }

  updateForm(publicCible: IPublicCible): void {
    this.editForm.patchValue({
      id: publicCible.id,
      libellePublic: publicCible.libellePublic,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const publicCible = this.createFromForm();

    if (publicCible.id !== undefined) {
      this.subscribeToSaveResponse(this.publicCibleService.update(publicCible));
    } else {
      this.subscribeToSaveResponse(this.publicCibleService.create(publicCible));
    }
  }

  private createFromForm(): IPublicCible {
    return {
      ...new PublicCible(),
      id: this.editForm.get(['id'])!.value,
      libellePublic: this.editForm.get(['libellePublic'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPublicCible>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('publicCibleListModification');

    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
