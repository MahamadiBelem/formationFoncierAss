import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInstallation } from 'app/shared/model/installation.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-installation-detail',
  templateUrl: './installation-detail.component.html',
})
export class InstallationDetailComponent implements OnInit {
  @Input() installation?: IInstallation;

  constructor(protected activatedRoute: ActivatedRoute, private activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    //this.activatedRoute.data.subscribe(({ installation }) => (this.installation = installation));
  }

  previousState(): void {
    window.history.back();
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
}
