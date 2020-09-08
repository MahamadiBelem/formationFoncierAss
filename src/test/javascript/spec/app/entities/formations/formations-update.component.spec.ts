import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationsUpdateComponent } from 'app/entities/formations/formations-update.component';
import { FormationsService } from 'app/entities/formations/formations.service';
import { Formations } from 'app/shared/model/formations.model';

describe('Component Tests', () => {
  describe('Formations Management Update Component', () => {
    let comp: FormationsUpdateComponent;
    let fixture: ComponentFixture<FormationsUpdateComponent>;
    let service: FormationsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FormationsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormationsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormationsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Formations(123);
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
        const entity = new Formations();
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
