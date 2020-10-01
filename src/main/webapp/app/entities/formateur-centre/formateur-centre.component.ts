import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormateurCentre } from 'app/shared/model/formateur-centre.model';
import { FormateurCentreService } from './formateur-centre.service';
import { FormateurCentreDeleteDialogComponent } from './formateur-centre-delete-dialog.component';

@Component({
  selector: 'jhi-formateur-centre',
  templateUrl: './formateur-centre.component.html',
})
export class FormateurCentreComponent implements OnInit, OnDestroy {
  formateurCentres?: IFormateurCentre[];
  eventSubscriber?: Subscription;

  constructor(
    protected formateurCentreService: FormateurCentreService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.formateurCentreService.query().subscribe((res: HttpResponse<IFormateurCentre[]>) => (this.formateurCentres = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFormateurCentres();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFormateurCentre): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFormateurCentres(): void {
    this.eventSubscriber = this.eventManager.subscribe('formateurCentreListModification', () => this.loadAll());
  }

  delete(formateurCentre: IFormateurCentre): void {
    const modalRef = this.modalService.open(FormateurCentreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formateurCentre = formateurCentre;
  }
}
