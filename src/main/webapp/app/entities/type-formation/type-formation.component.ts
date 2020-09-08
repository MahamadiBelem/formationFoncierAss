import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeFormation } from 'app/shared/model/type-formation.model';
import { TypeFormationService } from './type-formation.service';
import { TypeFormationDeleteDialogComponent } from './type-formation-delete-dialog.component';

@Component({
  selector: 'jhi-type-formation',
  templateUrl: './type-formation.component.html',
})
export class TypeFormationComponent implements OnInit, OnDestroy {
  typeFormations?: ITypeFormation[];
  eventSubscriber?: Subscription;

  constructor(
    protected typeFormationService: TypeFormationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typeFormationService.query().subscribe((res: HttpResponse<ITypeFormation[]>) => (this.typeFormations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeFormations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeFormation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeFormations(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeFormationListModification', () => this.loadAll());
  }

  delete(typeFormation: ITypeFormation): void {
    const modalRef = this.modalService.open(TypeFormationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeFormation = typeFormation;
  }
}
