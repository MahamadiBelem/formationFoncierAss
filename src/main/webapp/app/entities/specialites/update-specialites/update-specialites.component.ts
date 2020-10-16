import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { SpecialitesService } from '../specialites.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ISpecialites, Specialites } from 'app/shared/model/specialites.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-update-specialites',
  templateUrl: './update-specialites.component.html',
  styleUrls: ['./update-specialites.component.scss'],
})
export class UpdateSpecialitesComponent implements OnInit {
  @Input() public specialites: any;

  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleSpecialite: [null, [Validators.required]],
  });

  constructor(
    protected specialitesService: SpecialitesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private activemodale: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.updateForm(this.specialites);
  }

  updateForm(specialites: ISpecialites): void {
    this.editForm.patchValue({
      id: specialites.id,
      libelleSpecialite: specialites.libelleSpecialite,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specialites = this.createFromForm();

    if (specialites.id !== undefined) {
      this.subscribeToSaveResponse(this.specialitesService.update(specialites));
    } else {
      this.subscribeToSaveResponse(this.specialitesService.create(specialites));
    }
  }

  private createFromForm(): ISpecialites {
    return {
      ...new Specialites(),
      id: this.editForm.get(['id'])!.value,
      libelleSpecialite: this.editForm.get(['libelleSpecialite'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecialites>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('specialitesListModification');
    this.cancel();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
