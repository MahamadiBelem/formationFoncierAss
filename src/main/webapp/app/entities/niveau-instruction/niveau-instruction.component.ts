import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INiveauInstruction } from 'app/shared/model/niveau-instruction.model';
import { NiveauInstructionService } from './niveau-instruction.service';
import { NiveauInstructionDeleteDialogComponent } from './niveau-instruction-delete-dialog.component';

@Component({
  selector: 'jhi-niveau-instruction',
  templateUrl: './niveau-instruction.component.html',
})
export class NiveauInstructionComponent implements OnInit, OnDestroy {
  niveauInstructions?: INiveauInstruction[];
  eventSubscriber?: Subscription;

  constructor(
    protected niveauInstructionService: NiveauInstructionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.niveauInstructionService
      .query()
      .subscribe((res: HttpResponse<INiveauInstruction[]>) => (this.niveauInstructions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNiveauInstructions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INiveauInstruction): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNiveauInstructions(): void {
    this.eventSubscriber = this.eventManager.subscribe('niveauInstructionListModification', () => this.loadAll());
  }

  delete(niveauInstruction: INiveauInstruction): void {
    const modalRef = this.modalService.open(NiveauInstructionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.niveauInstruction = niveauInstruction;
  }
}
