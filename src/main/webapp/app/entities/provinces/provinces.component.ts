import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProvinces } from 'app/shared/model/provinces.model';
import { ProvincesService } from './provinces.service';
import { ProvincesDeleteDialogComponent } from './provinces-delete-dialog.component';

@Component({
  selector: 'jhi-provinces',
  templateUrl: './provinces.component.html',
})
export class ProvincesComponent implements OnInit, OnDestroy {
  provinces?: IProvinces[];
  eventSubscriber?: Subscription;

  constructor(protected provincesService: ProvincesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.provincesService.query().subscribe((res: HttpResponse<IProvinces[]>) => (this.provinces = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProvinces();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProvinces): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProvinces(): void {
    this.eventSubscriber = this.eventManager.subscribe('provincesListModification', () => this.loadAll());
  }

  delete(provinces: IProvinces): void {
    const modalRef = this.modalService.open(ProvincesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.provinces = provinces;
  }
}
