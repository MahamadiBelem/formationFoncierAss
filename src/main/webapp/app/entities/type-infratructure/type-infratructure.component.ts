import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeInfratructure } from 'app/shared/model/type-infratructure.model';
import { TypeInfratructureService } from './type-infratructure.service';
import { TypeInfratructureDeleteDialogComponent } from './type-infratructure-delete-dialog.component';

@Component({
  selector: 'jhi-type-infratructure',
  templateUrl: './type-infratructure.component.html',
})
export class TypeInfratructureComponent implements OnInit, OnDestroy {
  typeInfratructures?: ITypeInfratructure[];
  eventSubscriber?: Subscription;

  constructor(
    protected typeInfratructureService: TypeInfratructureService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typeInfratructureService
      .query()
      .subscribe((res: HttpResponse<ITypeInfratructure[]>) => (this.typeInfratructures = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeInfratructures();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeInfratructure): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeInfratructures(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeInfratructureListModification', () => this.loadAll());
  }

  delete(typeInfratructure: ITypeInfratructure): void {
    const modalRef = this.modalService.open(TypeInfratructureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeInfratructure = typeInfratructure;
  }
}
