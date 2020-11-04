import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeActe } from 'app/shared/model/type-acte.model';
import { TypeActeService } from './type-acte.service';
import { TypeActeDeleteDialogComponent } from './type-acte-delete-dialog.component';

@Component({
  selector: 'jhi-type-acte',
  templateUrl: './type-acte.component.html',
})
export class TypeActeComponent implements OnInit, OnDestroy {
  typeActes?: ITypeActe[];
  eventSubscriber?: Subscription;

  constructor(protected typeActeService: TypeActeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.typeActeService.query().subscribe((res: HttpResponse<ITypeActe[]>) => (this.typeActes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeActes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeActe): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeActes(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeActeListModification', () => this.loadAll());
  }

  delete(typeActe: ITypeActe): void {
    const modalRef = this.modalService.open(TypeActeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeActe = typeActe;
  }
}
