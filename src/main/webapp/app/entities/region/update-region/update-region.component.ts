import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { RegionService } from '../region.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IRegion, Region } from 'app/shared/model/region.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-update-region',
  templateUrl: './update-region.component.html',
  styleUrls: ['./update-region.component.scss'],
})
export class UpdateRegionComponent implements OnInit {
  isSaving = false;
  @Input() public region: any;

  editForm = this.fb.group({
    id: [],
    libelleRegion: [null, [Validators.required]],
  });

  constructor(
    protected regionService: RegionService,
    protected activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.updateForm(this.region);
  }

  updateForm(region: any): void {
    this.editForm.patchValue({
      id: region.id,
      libelleRegion: region.libelleRegion,
    });
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegion>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }
  save(): void {
    this.isSaving = true;
    const region = this.createFromForm();
    if (region.id !== undefined) {
      this.subscribeToSaveResponse(this.regionService.update(region));
    } else {
      this.subscribeToSaveResponse(this.regionService.create(region));
    }
    this.eventManager.broadcast('regionListModification');
    this.activeModal.close();
  }

  private createFromForm(): IRegion {
    return {
      ...new Region(),
      id: this.editForm.get(['id'])!.value,
      libelleRegion: this.editForm.get(['libelleRegion'])!.value,
    };
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
