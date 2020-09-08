import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { AmenagementUpdateComponent } from 'app/entities/amenagement/amenagement-update.component';
import { AmenagementService } from 'app/entities/amenagement/amenagement.service';
import { Amenagement } from 'app/shared/model/amenagement.model';

describe('Component Tests', () => {
  describe('Amenagement Management Update Component', () => {
    let comp: AmenagementUpdateComponent;
    let fixture: ComponentFixture<AmenagementUpdateComponent>;
    let service: AmenagementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [AmenagementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AmenagementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AmenagementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AmenagementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Amenagement(123);
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
        const entity = new Amenagement();
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
