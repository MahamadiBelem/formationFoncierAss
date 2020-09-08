import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommunes } from 'app/shared/model/communes.model';
import { CommunesService } from './communes.service';
import { CommunesDeleteDialogComponent } from './communes-delete-dialog.component';

@Component({
  selector: 'jhi-communes',
  templateUrl: './communes.component.html',
})
export class CommunesComponent implements OnInit, OnDestroy {
  communes?: ICommunes[];
  eventSubscriber?: Subscription;

  constructor(protected communesService: CommunesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.communesService.query().subscribe((res: HttpResponse<ICommunes[]>) => (this.communes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCommunes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICommunes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCommunes(): void {
    this.eventSubscriber = this.eventManager.subscribe('communesListModification', () => this.loadAll());
  }

  delete(communes: ICommunes): void {
    const modalRef = this.modalService.open(CommunesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.communes = communes;
  }
}
