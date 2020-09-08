import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeAmenagementUpdateComponent } from 'app/entities/type-amenagement/type-amenagement-update.component';
import { TypeAmenagementService } from 'app/entities/type-amenagement/type-amenagement.service';
import { TypeAmenagement } from 'app/shared/model/type-amenagement.model';

describe('Component Tests', () => {
  describe('TypeAmenagement Management Update Component', () => {
    let comp: TypeAmenagementUpdateComponent;
    let fixture: ComponentFixture<TypeAmenagementUpdateComponent>;
    let service: TypeAmenagementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeAmenagementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeAmenagementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAmenagementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAmenagementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeAmenagement(123);
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
        const entity = new TypeAmenagement();
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
