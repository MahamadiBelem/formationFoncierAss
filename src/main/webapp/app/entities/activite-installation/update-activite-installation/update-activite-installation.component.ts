import { Component, Input, OnInit } from '@angular/core';
import { ICompositionKits } from '../../../shared/model/composition-kits.model';
import { FormBuilder, Validators } from '@angular/forms';
import { ActiviteInstallationService } from '../activite-installation.service';
import { CompositionKitsService } from '../../composition-kits/composition-kits.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';
import { ActiviteInstallation, IActiviteInstallation } from '../../../shared/model/activite-installation.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-update-activite-installation',
  templateUrl: './update-activite-installation.component.html',
  styleUrls: ['./update-activite-installation.component.scss'],
})
export class UpdateActiviteInstallationComponent implements OnInit {
  isSaving = false;
  @Input() public activiteInstallation: any;
  compositionkits: ICompositionKits[] = [];

  editForm = this.fb.group({
    id: [],
    libelleActivite: [null, [Validators.required]],
    kits: [],
  });

  constructor(
    protected activiteInstallationService: ActiviteInstallationService,
    protected compositionKitsService: CompositionKitsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.updateForm(this.activiteInstallation);
    this.compositionKitsService.query().subscribe((res: HttpResponse<ICompositionKits[]>) => (this.compositionkits = res.body || []));

    /* this.activatedRoute.data.subscribe(({ activiteInstallation }) => {



    });*/
  }

  updateForm(activiteInstallation: IActiviteInstallation): void {
    this.editForm.patchValue({
      id: activiteInstallation.id,
      libelleActivite: activiteInstallation.libelleActivite,
      kits: activiteInstallation.kits,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const activiteInstallation = this.createFromForm();
    if (activiteInstallation.id !== undefined) {
      this.subscribeToSaveResponse(this.activiteInstallationService.update(activiteInstallation));
    } else {
      this.subscribeToSaveResponse(this.activiteInstallationService.create(activiteInstallation));
    }
  }

  private createFromForm(): IActiviteInstallation {
    return {
      ...new ActiviteInstallation(),
      id: this.editForm.get(['id'])!.value,
      libelleActivite: this.editForm.get(['libelleActivite'])!.value,
      kits: this.editForm.get(['kits'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActiviteInstallation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('activiteInstallationListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICompositionKits): any {
    return item.id;
  }

  getSelected(selectedVals: ICompositionKits[], option: ICompositionKits): ICompositionKits {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
  cancel(): void {
    this.activemodale.dismiss();
  }
}
