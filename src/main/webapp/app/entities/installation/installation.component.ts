import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInstallation } from 'app/shared/model/installation.model';
import { InstallationService } from './installation.service';
import { InstallationDeleteDialogComponent } from './installation-delete-dialog.component';

@Component({
  selector: 'jhi-installation',
  templateUrl: './installation.component.html',
})
export class InstallationComponent implements OnInit, OnDestroy {
  installations?: IInstallation[];
  eventSubscriber?: Subscription;

  constructor(
    protected installationService: InstallationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.installationService.query().subscribe((res: HttpResponse<IInstallation[]>) => (this.installations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInstallations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInstallation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInstallations(): void {
    this.eventSubscriber = this.eventManager.subscribe('installationListModification', () => this.loadAll());
  }

  delete(installation: IInstallation): void {
    const modalRef = this.modalService.open(InstallationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.installation = installation;
  }
}
