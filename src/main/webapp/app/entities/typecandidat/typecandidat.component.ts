import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypecandidat } from 'app/shared/model/typecandidat.model';
import { TypecandidatService } from './typecandidat.service';
import { TypecandidatDeleteDialogComponent } from './typecandidat-delete-dialog.component';

@Component({
  selector: 'jhi-typecandidat',
  templateUrl: './typecandidat.component.html',
})
export class TypecandidatComponent implements OnInit, OnDestroy {
  typecandidats?: ITypecandidat[];
  eventSubscriber?: Subscription;

  constructor(
    protected typecandidatService: TypecandidatService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typecandidatService.query().subscribe((res: HttpResponse<ITypecandidat[]>) => (this.typecandidats = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypecandidats();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypecandidat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypecandidats(): void {
    this.eventSubscriber = this.eventManager.subscribe('typecandidatListModification', () => this.loadAll());
  }

  delete(typecandidat: ITypecandidat): void {
    const modalRef = this.modalService.open(TypecandidatDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typecandidat = typecandidat;
  }
}
