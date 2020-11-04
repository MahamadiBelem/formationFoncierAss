import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonnel, Personnel } from 'app/shared/model/personnel.model';
import { PersonnelService } from './personnel.service';
import { ISfr } from 'app/shared/model/sfr.model';
import { SfrService } from 'app/entities/sfr/sfr.service';
import { IProfilPersonnel } from 'app/shared/model/profil-personnel.model';
import { ProfilPersonnelService } from 'app/entities/profil-personnel/profil-personnel.service';

type SelectableEntity = ISfr | IProfilPersonnel;

@Component({
  selector: 'jhi-personnel-update',
  templateUrl: './personnel-update.component.html',
})
export class PersonnelUpdateComponent implements OnInit {
  isSaving = false;
  sfrs: ISfr[] = [];
  profilpersonnels: IProfilPersonnel[] = [];

  editForm = this.fb.group({
    id: [],
    nomPers: [],
    prenomPers: [],
    telPers: [],
    emailPers: [],
    sfrPersonnel: [],
    personnel: [],
  });

  constructor(
    protected personnelService: PersonnelService,
    protected sfrService: SfrService,
    protected profilPersonnelService: ProfilPersonnelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personnel }) => {
      this.updateForm(personnel);

      this.sfrService.query().subscribe((res: HttpResponse<ISfr[]>) => (this.sfrs = res.body || []));

      this.profilPersonnelService.query().subscribe((res: HttpResponse<IProfilPersonnel[]>) => (this.profilpersonnels = res.body || []));
    });
  }

  updateForm(personnel: IPersonnel): void {
    this.editForm.patchValue({
      id: personnel.id,
      nomPers: personnel.nomPers,
      prenomPers: personnel.prenomPers,
      telPers: personnel.telPers,
      emailPers: personnel.emailPers,
      sfrPersonnel: personnel.sfrPersonnel,
      personnel: personnel.personnel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personnel = this.createFromForm();
    if (personnel.id !== undefined) {
      this.subscribeToSaveResponse(this.personnelService.update(personnel));
    } else {
      this.subscribeToSaveResponse(this.personnelService.create(personnel));
    }
  }

  private createFromForm(): IPersonnel {
    return {
      ...new Personnel(),
      id: this.editForm.get(['id'])!.value,
      nomPers: this.editForm.get(['nomPers'])!.value,
      prenomPers: this.editForm.get(['prenomPers'])!.value,
      telPers: this.editForm.get(['telPers'])!.value,
      emailPers: this.editForm.get(['emailPers'])!.value,
      sfrPersonnel: this.editForm.get(['sfrPersonnel'])!.value,
      personnel: this.editForm.get(['personnel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonnel>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
