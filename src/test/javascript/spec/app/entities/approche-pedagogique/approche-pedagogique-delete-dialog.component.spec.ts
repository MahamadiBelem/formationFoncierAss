import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GestionFormationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ApprochePedagogiqueDeleteDialogComponent } from 'app/entities/approche-pedagogique/approche-pedagogique-delete-dialog.component';
import { ApprochePedagogiqueService } from 'app/entities/approche-pedagogique/approche-pedagogique.service';

describe('Component Tests', () => {
  describe('ApprochePedagogique Management Delete Component', () => {
    let comp: ApprochePedagogiqueDeleteDialogComponent;
    let fixture: ComponentFixture<ApprochePedagogiqueDeleteDialogComponent>;
    let service: ApprochePedagogiqueService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprochePedagogiqueDeleteDialogComponent],
      })
        .overrideTemplate(ApprochePedagogiqueDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApprochePedagogiqueDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprochePedagogiqueService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
