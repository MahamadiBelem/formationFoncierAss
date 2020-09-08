import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IApprenantes } from 'app/shared/model/apprenantes.model';
import { ApprenantesService } from './apprenantes.service';
import { ApprenantesDeleteDialogComponent } from './apprenantes-delete-dialog.component';

@Component({
  selector: 'jhi-apprenantes',
  templateUrl: './apprenantes.component.html',
})
export class ApprenantesComponent implements OnInit, OnDestroy {
  apprenantes?: IApprenantes[];
  eventSubscriber?: Subscription;

  constructor(
    protected apprenantesService: ApprenantesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.apprenantesService.query().subscribe((res: HttpResponse<IApprenantes[]>) => (this.apprenantes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInApprenantes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IApprenantes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInApprenantes(): void {
    this.eventSubscriber = this.eventManager.subscribe('apprenantesListModification', () => this.loadAll());
  }

  delete(apprenantes: IApprenantes): void {
    const modalRef = this.modalService.open(ApprenantesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.apprenantes = apprenantes;
  }
}
