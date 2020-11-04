import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeActeUpdateComponent } from 'app/entities/type-acte/type-acte-update.component';
import { TypeActeService } from 'app/entities/type-acte/type-acte.service';
import { TypeActe } from 'app/shared/model/type-acte.model';

describe('Component Tests', () => {
  describe('TypeActe Management Update Component', () => {
    let comp: TypeActeUpdateComponent;
    let fixture: ComponentFixture<TypeActeUpdateComponent>;
    let service: TypeActeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeActeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeActeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeActeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeActeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeActe(123);
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
        const entity = new TypeActe();
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
