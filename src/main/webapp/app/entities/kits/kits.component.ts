import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IKits } from 'app/shared/model/kits.model';
import { KitsService } from './kits.service';
import { KitsDeleteDialogComponent } from './kits-delete-dialog.component';

@Component({
  selector: 'jhi-kits',
  templateUrl: './kits.component.html',
})
export class KitsComponent implements OnInit, OnDestroy {
  kits?: IKits[];
  eventSubscriber?: Subscription;

  constructor(protected kitsService: KitsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.kitsService.query().subscribe((res: HttpResponse<IKits[]>) => (this.kits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInKits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IKits): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInKits(): void {
    this.eventSubscriber = this.eventManager.subscribe('kitsListModification', () => this.loadAll());
  }

  delete(kits: IKits): void {
    const modalRef = this.modalService.open(KitsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.kits = kits;
  }
}
