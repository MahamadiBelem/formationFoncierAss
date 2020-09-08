import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPromoteurs } from 'app/shared/model/promoteurs.model';
import { PromoteursService } from './promoteurs.service';
import { PromoteursDeleteDialogComponent } from './promoteurs-delete-dialog.component';

@Component({
  selector: 'jhi-promoteurs',
  templateUrl: './promoteurs.component.html',
})
export class PromoteursComponent implements OnInit, OnDestroy {
  promoteurs?: IPromoteurs[];
  eventSubscriber?: Subscription;

  constructor(protected promoteursService: PromoteursService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.promoteursService.query().subscribe((res: HttpResponse<IPromoteurs[]>) => (this.promoteurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPromoteurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPromoteurs): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPromoteurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('promoteursListModification', () => this.loadAll());
  }

  delete(promoteurs: IPromoteurs): void {
    const modalRef = this.modalService.open(PromoteursDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.promoteurs = promoteurs;
  }
}
