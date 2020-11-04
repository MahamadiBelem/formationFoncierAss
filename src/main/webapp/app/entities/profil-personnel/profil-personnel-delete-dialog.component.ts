import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilPersonnel } from 'app/shared/model/profil-personnel.model';
import { ProfilPersonnelService } from './profil-personnel.service';

@Component({
  templateUrl: './profil-personnel-delete-dialog.component.html',
})
export class ProfilPersonnelDeleteDialogComponent {
  profilPersonnel?: IProfilPersonnel;

  constructor(
    protected profilPersonnelService: ProfilPersonnelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.profilPersonnelService.delete(id).subscribe(() => {
      this.eventManager.broadcast('profilPersonnelListModification');
      this.activeModal.close();
    });
  }
}
