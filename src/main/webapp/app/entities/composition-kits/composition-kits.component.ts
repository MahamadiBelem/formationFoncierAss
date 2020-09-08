import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompositionKits } from 'app/shared/model/composition-kits.model';
import { CompositionKitsService } from './composition-kits.service';
import { CompositionKitsDeleteDialogComponent } from './composition-kits-delete-dialog.component';

@Component({
  selector: 'jhi-composition-kits',
  templateUrl: './composition-kits.component.html',
})
export class CompositionKitsComponent implements OnInit, OnDestroy {
  compositionKits?: ICompositionKits[];
  eventSubscriber?: Subscription;

  constructor(
    protected compositionKitsService: CompositionKitsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.compositionKitsService.query().subscribe((res: HttpResponse<ICompositionKits[]>) => (this.compositionKits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCompositionKits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICompositionKits): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCompositionKits(): void {
    this.eventSubscriber = this.eventManager.subscribe('compositionKitsListModification', () => this.loadAll());
  }

  delete(compositionKits: ICompositionKits): void {
    const modalRef = this.modalService.open(CompositionKitsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.compositionKits = compositionKits;
  }
}
