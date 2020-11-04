import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProfilPersonnel } from 'app/shared/model/profil-personnel.model';
import { ProfilPersonnelService } from './profil-personnel.service';
import { ProfilPersonnelDeleteDialogComponent } from './profil-personnel-delete-dialog.component';

@Component({
  selector: 'jhi-profil-personnel',
  templateUrl: './profil-personnel.component.html',
})
export class ProfilPersonnelComponent implements OnInit, OnDestroy {
  profilPersonnels?: IProfilPersonnel[];
  eventSubscriber?: Subscription;

  constructor(
    protected profilPersonnelService: ProfilPersonnelService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.profilPersonnelService.query().subscribe((res: HttpResponse<IProfilPersonnel[]>) => (this.profilPersonnels = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProfilPersonnels();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProfilPersonnel): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProfilPersonnels(): void {
    this.eventSubscriber = this.eventManager.subscribe('profilPersonnelListModification', () => this.loadAll());
  }

  delete(profilPersonnel: IProfilPersonnel): void {
    const modalRef = this.modalService.open(ProfilPersonnelDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.profilPersonnel = profilPersonnel;
  }
}
