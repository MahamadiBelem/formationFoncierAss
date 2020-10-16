import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPublicCible } from 'app/shared/model/public-cible.model';
import { PublicCibleService } from './public-cible.service';
import { PublicCibleDeleteDialogComponent } from './public-cible-delete-dialog.component';
import { SavePublicCibleComponent } from './save-public-cible/save-public-cible.component';
import { UpdatePublicCibleComponent } from './update-public-cible/update-public-cible.component';

@Component({
  selector: 'jhi-public-cible',
  templateUrl: './public-cible.component.html',
})
export class PublicCibleComponent implements OnInit, OnDestroy {
  publicCibles?: IPublicCible[];
  eventSubscriber?: Subscription;

  constructor(
    protected publicCibleService: PublicCibleService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.publicCibleService.query().subscribe((res: HttpResponse<IPublicCible[]>) => (this.publicCibles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPublicCibles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPublicCible): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPublicCibles(): void {
    this.eventSubscriber = this.eventManager.subscribe('publicCibleListModification', () => this.loadAll());
  }

  delete(publicCible: IPublicCible): void {
    const modalRef = this.modalService.open(PublicCibleDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.publicCible = publicCible;
  }

  savemodal(): void {
    const savemodale = this.modalService.open(SavePublicCibleComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodal(publicCible: IPublicCible): void {
    const updatemodale = this.modalService.open(UpdatePublicCibleComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.publicCible = publicCible;
  }
}
