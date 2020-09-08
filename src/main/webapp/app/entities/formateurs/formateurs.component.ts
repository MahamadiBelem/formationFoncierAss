import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormateurs } from 'app/shared/model/formateurs.model';
import { FormateursService } from './formateurs.service';
import { FormateursDeleteDialogComponent } from './formateurs-delete-dialog.component';

@Component({
  selector: 'jhi-formateurs',
  templateUrl: './formateurs.component.html',
})
export class FormateursComponent implements OnInit, OnDestroy {
  formateurs?: IFormateurs[];
  eventSubscriber?: Subscription;

  constructor(protected formateursService: FormateursService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.formateursService.query().subscribe((res: HttpResponse<IFormateurs[]>) => (this.formateurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFormateurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFormateurs): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFormateurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('formateursListModification', () => this.loadAll());
  }

  delete(formateurs: IFormateurs): void {
    const modalRef = this.modalService.open(FormateursDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formateurs = formateurs;
  }
}
