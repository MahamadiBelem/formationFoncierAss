import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilPersonnel } from 'app/shared/model/profil-personnel.model';

@Component({
  selector: 'jhi-profil-personnel-detail',
  templateUrl: './profil-personnel-detail.component.html',
})
export class ProfilPersonnelDetailComponent implements OnInit {
  profilPersonnel: IProfilPersonnel | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ profilPersonnel }) => (this.profilPersonnel = profilPersonnel));
  }

  previousState(): void {
    window.history.back();
  }
}
