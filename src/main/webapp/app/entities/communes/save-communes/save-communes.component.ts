import { Component, OnInit } from '@angular/core';
import { IProvinces } from 'app/shared/model/provinces.model';
import { Validators, FormBuilder } from '@angular/forms';
import { CommunesService } from '../communes.service';
import { ProvincesService } from 'app/entities/provinces/provinces.service';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { ICommunes, Communes } from 'app/shared/model/communes.model';
import { Observable } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-save-communes',
  templateUrl: './save-communes.component.html',
  styleUrls: ['./save-communes.component.scss'],
})
export class SaveCommunesComponent implements OnInit {
  isSaving = false;
  provinces: IProvinces[] = [];

  editForm = this.fb.group({
    id: [],
    libelleCommune: [null, [Validators.required]],
    provinces: [],
  });

  constructor(
    protected communesService: CommunesService,
    protected provincesService: ProvincesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ communes }) => {
      this.updateForm(communes);

      this.provincesService.query().subscribe((res: HttpResponse<IProvinces[]>) => (this.provinces = res.body || []));
    });
  }

  updateForm(communes: ICommunes): void {
    this.editForm.patchValue({
      id: communes.id,
      libelleCommune: communes.libelleCommune,
      provinces: communes.provinces,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const communes = this.createFromForm();
    this.subscribeToSaveResponse(this.communesService.create(communes));
    /*
    if (communes.id !== undefined) {
      this.subscribeToSaveResponse(this.communesService.update(communes));
    } else {
      
    }
    */
  }

  private createFromForm(): ICommunes {
    return {
      ...new Communes(),
      id: this.editForm.get(['id'])!.value,
      libelleCommune: this.editForm.get(['libelleCommune'])!.value,
      provinces: this.editForm.get(['provinces'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommunes>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('communesListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IProvinces): any {
    return item.id;
  }

  cancel(): void {
    this.activemodal.dismiss();
  }
}
