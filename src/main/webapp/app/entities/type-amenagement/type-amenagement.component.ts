import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeAmenagement } from 'app/shared/model/type-amenagement.model';
import { TypeAmenagementService } from './type-amenagement.service';
import { TypeAmenagementDeleteDialogComponent } from './type-amenagement-delete-dialog.component';

@Component({
  selector: 'jhi-type-amenagement',
  templateUrl: './type-amenagement.component.html',
})
export class TypeAmenagementComponent implements OnInit, OnDestroy {
  typeAmenagements?: ITypeAmenagement[];
  eventSubscriber?: Subscription;

  constructor(
    protected typeAmenagementService: TypeAmenagementService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typeAmenagementService.query().subscribe((res: HttpResponse<ITypeAmenagement[]>) => (this.typeAmenagements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeAmenagements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeAmenagement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeAmenagements(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeAmenagementListModification', () => this.loadAll());
  }

  delete(typeAmenagement: ITypeAmenagement): void {
    const modalRef = this.modalService.open(TypeAmenagementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeAmenagement = typeAmenagement;
  }
}
