import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVillages } from 'app/shared/model/villages.model';
import { VillagesService } from './villages.service';
import { VillagesDeleteDialogComponent } from './villages-delete-dialog.component';

@Component({
  selector: 'jhi-villages',
  templateUrl: './villages.component.html',
})
export class VillagesComponent implements OnInit, OnDestroy {
  villages?: IVillages[];
  eventSubscriber?: Subscription;

  constructor(protected villagesService: VillagesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.villagesService.query().subscribe((res: HttpResponse<IVillages[]>) => (this.villages = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVillages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVillages): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVillages(): void {
    this.eventSubscriber = this.eventManager.subscribe('villagesListModification', () => this.loadAll());
  }

  delete(villages: IVillages): void {
    const modalRef = this.modalService.open(VillagesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.villages = villages;
  }
}
