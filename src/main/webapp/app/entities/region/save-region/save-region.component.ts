import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators } from '@angular/forms';
import { RegionService } from '../region.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { IRegion, Region } from 'app/shared/model/region.model';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-region',
  templateUrl: './save-region.component.html',
  styleUrls: ['./save-region.component.scss'],
})
export class SaveRegionComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleRegion: [null, [Validators.required]],
  });

  constructor(
    protected regionService: RegionService,
    protected activatedRoute: ActivatedRoute,
    public activeModal: NgbActiveModal,
    private fb: FormBuilder,
    private route: Router,
    protected eventManager: JhiEventManager
  ) {}

  updateForm(region: IRegion): void {
    this.editForm.patchValue({
      id: region.id,
      libelleRegion: region.libelleRegion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    const region = this.createFromForm();
    this.isSaving = true;
    this.subscribeToSaveResponse(this.regionService.create(region));
    this.eventManager.broadcast('regionListModification');
    this.activeModal.close();

    /*
    if (region.id !== undefined) {
      this.subscribeToSaveResponse(this.regionService.update(region));
    } else {
      
    }

    */
  }

  private createFromForm(): IRegion {
    return {
      ...new Region(),
      id: this.editForm.get(['id'])!.value,
      libelleRegion: this.editForm.get(['libelleRegion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegion>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  ngOnInit(): void {}

  cancel(): void {
    this.activeModal.dismiss();
  }
}
