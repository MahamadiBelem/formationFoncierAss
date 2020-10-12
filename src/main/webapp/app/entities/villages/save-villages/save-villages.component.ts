import { Component, OnInit } from '@angular/core';
import { ICommunes } from 'app/shared/model/communes.model';
import { Validators, FormBuilder } from '@angular/forms';
import { VillagesService } from '../villages.service';
import { CommunesService } from 'app/entities/communes/communes.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { IVillages, Villages } from 'app/shared/model/villages.model';
import { Observable } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-villages',
  templateUrl: './save-villages.component.html',
  styleUrls: ['./save-villages.component.scss'],
})
export class SaveVillagesComponent implements OnInit {
  isSaving = false;
  communes: ICommunes[] = [];

  editForm = this.fb.group({
    id: [],
    libelleVillage: [null, [Validators.required]],
    commune: [],
  });

  constructor(
    protected villagesService: VillagesService,
    protected communesService: CommunesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.communesService.query().subscribe((res: HttpResponse<ICommunes[]>) => (this.communes = res.body || []));

    this.activatedRoute.data.subscribe(({ villages }) => {
      this.updateForm(villages);
    });
  }

  updateForm(villages: IVillages): void {
    this.editForm.patchValue({
      id: villages.id,
      libelleVillage: villages.libelleVillage,
      commune: villages.commune,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const villages = this.createFromForm();
    this.subscribeToSaveResponse(this.villagesService.create(villages));
    /*
    if (villages.id !== undefined) {
      this.subscribeToSaveResponse(this.villagesService.update(villages));
    } else {
      
    }
    */
  }

  private createFromForm(): IVillages {
    return {
      ...new Villages(),
      id: this.editForm.get(['id'])!.value,
      libelleVillage: this.editForm.get(['libelleVillage'])!.value,
      commune: this.editForm.get(['commune'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVillages>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('villagesListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICommunes): any {
    return item.id;
  }

  cancel(): void {
    this.activemodal.dismiss();
  }
}
