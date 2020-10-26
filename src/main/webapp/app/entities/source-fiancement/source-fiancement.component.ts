import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISourceFiancement } from 'app/shared/model/source-fiancement.model';
import { SourceFiancementService } from './source-fiancement.service';
import { SourceFiancementDeleteDialogComponent } from './source-fiancement-delete-dialog.component';
import { SaveSourceFinancementComponent } from './save-source-financement/save-source-financement.component';
import { UpdateSourceFinancementComponent } from './update-source-financement/update-source-financement.component';

@Component({
  selector: 'jhi-source-fiancement',
  templateUrl: './source-fiancement.component.html',
})
export class SourceFiancementComponent implements OnInit, OnDestroy {
  sourceFiancements?: ISourceFiancement[];
  eventSubscriber?: Subscription;

  constructor(
    protected sourceFiancementService: SourceFiancementService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.sourceFiancementService.query().subscribe((res: HttpResponse<ISourceFiancement[]>) => (this.sourceFiancements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSourceFiancements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISourceFiancement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSourceFiancements(): void {
    this.eventSubscriber = this.eventManager.subscribe('sourceFiancementListModification', () => this.loadAll());
  }

  delete(sourceFiancement: ISourceFiancement): void {
    const modalRef = this.modalService.open(SourceFiancementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sourceFiancement = sourceFiancement;
  }

  savemodale(): void {
    const savemodal = this.modalService.open(SaveSourceFinancementComponent, { size: 'lg', backdrop: 'static' });
  }

  updatemodale(sourceFiancement: ISourceFiancement): void {
    const updatemodal = this.modalService.open(UpdateSourceFinancementComponent, { size: 'lg', backdrop: 'static' });
    updatemodal.componentInstance.sourceFiancement = sourceFiancement;
  }
}
