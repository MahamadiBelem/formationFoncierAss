import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegime } from 'app/shared/model/regime.model';
import { RegimeService } from './regime.service';
import { RegimeDeleteDialogComponent } from './regime-delete-dialog.component';

@Component({
  selector: 'jhi-regime',
  templateUrl: './regime.component.html',
})
export class RegimeComponent implements OnInit, OnDestroy {
  regimes?: IRegime[];
  eventSubscriber?: Subscription;

  constructor(protected regimeService: RegimeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.regimeService.query().subscribe((res: HttpResponse<IRegime[]>) => (this.regimes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRegimes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRegime): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRegimes(): void {
    this.eventSubscriber = this.eventManager.subscribe('regimeListModification', () => this.loadAll());
  }

  delete(regime: IRegime): void {
    const modalRef = this.modalService.open(RegimeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.regime = regime;
  }
}
