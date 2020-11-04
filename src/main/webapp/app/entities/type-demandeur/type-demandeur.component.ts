import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeDemandeur } from 'app/shared/model/type-demandeur.model';
import { TypeDemandeurService } from './type-demandeur.service';
import { TypeDemandeurDeleteDialogComponent } from './type-demandeur-delete-dialog.component';

@Component({
  selector: 'jhi-type-demandeur',
  templateUrl: './type-demandeur.component.html',
})
export class TypeDemandeurComponent implements OnInit, OnDestroy {
  typeDemandeurs?: ITypeDemandeur[];
  eventSubscriber?: Subscription;

  constructor(
    protected typeDemandeurService: TypeDemandeurService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typeDemandeurService.query().subscribe((res: HttpResponse<ITypeDemandeur[]>) => (this.typeDemandeurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeDemandeurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeDemandeur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeDemandeurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeDemandeurListModification', () => this.loadAll());
  }

  delete(typeDemandeur: ITypeDemandeur): void {
    const modalRef = this.modalService.open(TypeDemandeurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeDemandeur = typeDemandeur;
  }
}
