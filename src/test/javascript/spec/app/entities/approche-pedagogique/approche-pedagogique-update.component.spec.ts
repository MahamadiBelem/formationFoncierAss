import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ApprochePedagogiqueUpdateComponent } from 'app/entities/approche-pedagogique/approche-pedagogique-update.component';
import { ApprochePedagogiqueService } from 'app/entities/approche-pedagogique/approche-pedagogique.service';
import { ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';

describe('Component Tests', () => {
  describe('ApprochePedagogique Management Update Component', () => {
    let comp: ApprochePedagogiqueUpdateComponent;
    let fixture: ComponentFixture<ApprochePedagogiqueUpdateComponent>;
    let service: ApprochePedagogiqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprochePedagogiqueUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ApprochePedagogiqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApprochePedagogiqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprochePedagogiqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApprochePedagogique(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApprochePedagogique();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
