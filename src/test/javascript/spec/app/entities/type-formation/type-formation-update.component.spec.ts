import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeFormationUpdateComponent } from 'app/entities/type-formation/type-formation-update.component';
import { TypeFormationService } from 'app/entities/type-formation/type-formation.service';
import { TypeFormation } from 'app/shared/model/type-formation.model';

describe('Component Tests', () => {
  describe('TypeFormation Management Update Component', () => {
    let comp: TypeFormationUpdateComponent;
    let fixture: ComponentFixture<TypeFormationUpdateComponent>;
    let service: TypeFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeFormationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeFormation(123);
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
        const entity = new TypeFormation();
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
