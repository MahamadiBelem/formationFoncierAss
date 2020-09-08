import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConditionAccess } from 'app/shared/model/condition-access.model';
import { ConditionAccessService } from './condition-access.service';
import { ConditionAccessDeleteDialogComponent } from './condition-access-delete-dialog.component';

@Component({
  selector: 'jhi-condition-access',
  templateUrl: './condition-access.component.html',
})
export class ConditionAccessComponent implements OnInit, OnDestroy {
  conditionAccesses?: IConditionAccess[];
  eventSubscriber?: Subscription;

  constructor(
    protected conditionAccessService: ConditionAccessService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.conditionAccessService.query().subscribe((res: HttpResponse<IConditionAccess[]>) => (this.conditionAccesses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConditionAccesses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConditionAccess): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConditionAccesses(): void {
    this.eventSubscriber = this.eventManager.subscribe('conditionAccessListModification', () => this.loadAll());
  }

  delete(conditionAccess: IConditionAccess): void {
    const modalRef = this.modalService.open(ConditionAccessDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.conditionAccess = conditionAccess;
  }
}
