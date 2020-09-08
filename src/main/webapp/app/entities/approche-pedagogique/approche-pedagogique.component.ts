import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';
import { ApprochePedagogiqueService } from './approche-pedagogique.service';
import { ApprochePedagogiqueDeleteDialogComponent } from './approche-pedagogique-delete-dialog.component';

@Component({
  selector: 'jhi-approche-pedagogique',
  templateUrl: './approche-pedagogique.component.html',
})
export class ApprochePedagogiqueComponent implements OnInit, OnDestroy {
  approchePedagogiques?: IApprochePedagogique[];
  eventSubscriber?: Subscription;

  constructor(
    protected approchePedagogiqueService: ApprochePedagogiqueService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.approchePedagogiqueService
      .query()
      .subscribe((res: HttpResponse<IApprochePedagogique[]>) => (this.approchePedagogiques = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInApprochePedagogiques();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IApprochePedagogique): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInApprochePedagogiques(): void {
    this.eventSubscriber = this.eventManager.subscribe('approchePedagogiqueListModification', () => this.loadAll());
  }

  delete(approchePedagogique: IApprochePedagogique): void {
    const modalRef = this.modalService.open(ApprochePedagogiqueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.approchePedagogique = approchePedagogique;
  }
}
