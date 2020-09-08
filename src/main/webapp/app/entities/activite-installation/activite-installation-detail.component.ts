import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';

@Component({
  selector: 'jhi-activite-installation-detail',
  templateUrl: './activite-installation-detail.component.html',
})
export class ActiviteInstallationDetailComponent implements OnInit {
  activiteInstallation: IActiviteInstallation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ activiteInstallation }) => (this.activiteInstallation = activiteInstallation));
  }

  previousState(): void {
    window.history.back();
  }
}
