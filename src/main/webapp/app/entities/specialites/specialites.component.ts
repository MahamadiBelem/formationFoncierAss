import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISpecialites } from 'app/shared/model/specialites.model';
import { SpecialitesService } from './specialites.service';
import { SpecialitesDeleteDialogComponent } from './specialites-delete-dialog.component';
import { SaveSpecialitesComponent } from './save-specialites/save-specialites.component';
import { UpdatePromoteurComponent } from '../promoteurs/update-promoteur/update-promoteur.component';
import { UpdateSpecialitesComponent } from './update-specialites/update-specialites.component';

@Component({
  selector: 'jhi-specialites',
  templateUrl: './specialites.component.html',
})
export class SpecialitesComponent implements OnInit, OnDestroy {
  specialites?: ISpecialites[];
  eventSubscriber?: Subscription;

  constructor(
    protected specialitesService: SpecialitesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.specialitesService.query().subscribe((res: HttpResponse<ISpecialites[]>) => (this.specialites = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSpecialites();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISpecialites): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSpecialites(): void {
    this.eventSubscriber = this.eventManager.subscribe('specialitesListModification', () => this.loadAll());
  }

  delete(specialites: ISpecialites): void {
    const modalRef = this.modalService.open(SpecialitesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.specialites = specialites;
  }

  savemodal(): void {
    const savemodale = this.modalService.open(SaveSpecialitesComponent, { size: 'lg', backdrop: 'static' });
  }
  updatemodal(specialites: ISpecialites): void {
    const updatemodale = this.modalService.open(UpdateSpecialitesComponent, { size: 'lg', backdrop: 'static' });
    updatemodale.componentInstance.specialites = specialites;
  }
}
