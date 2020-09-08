import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormateursUpdateComponent } from 'app/entities/formateurs/formateurs-update.component';
import { FormateursService } from 'app/entities/formateurs/formateurs.service';
import { Formateurs } from 'app/shared/model/formateurs.model';

describe('Component Tests', () => {
  describe('Formateurs Management Update Component', () => {
    let comp: FormateursUpdateComponent;
    let fixture: ComponentFixture<FormateursUpdateComponent>;
    let service: FormateursService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormateursUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FormateursUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormateursUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormateursService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Formateurs(123);
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
        const entity = new Formateurs();
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
