import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IActiviteInstallation } from 'app/shared/model/activite-installation.model';
import { ActiviteInstallationService } from './activite-installation.service';
import { ActiviteInstallationDeleteDialogComponent } from './activite-installation-delete-dialog.component';
import { SaveActiviteInstallationComponent } from './save-activite-installation/save-activite-installation.component';
import { UpdateActiviteInstallationComponent } from './update-activite-installation/update-activite-installation.component';

@Component({
  selector: 'jhi-activite-installation',
  templateUrl: './activite-installation.component.html',
})
export class ActiviteInstallationComponent implements OnInit, OnDestroy {
  activiteInstallations?: IActiviteInstallation[];
  eventSubscriber?: Subscription;

  constructor(
    protected activiteInstallationService: ActiviteInstallationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.activiteInstallationService
      .query()
      .subscribe((res: HttpResponse<IActiviteInstallation[]>) => (this.activiteInstallations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInActiviteInstallations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IActiviteInstallation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInActiviteInstallations(): void {
    this.eventSubscriber = this.eventManager.subscribe('activiteInstallationListModification', () => this.loadAll());
  }

  delete(activiteInstallation: IActiviteInstallation): void {
    const modalRef = this.modalService.open(ActiviteInstallationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.activiteInstallation = activiteInstallation;
  }

  savemodale(): void {
    const savemodal = this.modalService.open(SaveActiviteInstallationComponent, { size: 'lg', backdrop: 'static' });
  }
  updatemodale(activiteInstallation: IActiviteInstallation): void {
    const updatemodale = this.modalService.open(UpdateActiviteInstallationComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.activiteInstallation = activiteInstallation;
  }
}
