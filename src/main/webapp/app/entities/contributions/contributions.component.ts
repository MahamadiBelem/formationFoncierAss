import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContributions } from 'app/shared/model/contributions.model';
import { ContributionsService } from './contributions.service';
import { ContributionsDeleteDialogComponent } from './contributions-delete-dialog.component';

@Component({
  selector: 'jhi-contributions',
  templateUrl: './contributions.component.html',
})
export class ContributionsComponent implements OnInit, OnDestroy {
  contributions?: IContributions[];
  eventSubscriber?: Subscription;

  constructor(
    protected contributionsService: ContributionsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.contributionsService.query().subscribe((res: HttpResponse<IContributions[]>) => (this.contributions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContributions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContributions): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContributions(): void {
    this.eventSubscriber = this.eventManager.subscribe('contributionsListModification', () => this.loadAll());
  }

  delete(contributions: IContributions): void {
    const modalRef = this.modalService.open(ContributionsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contributions = contributions;
  }
}
