import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ProvincesUpdateComponent } from 'app/entities/provinces/provinces-update.component';
import { ProvincesService } from 'app/entities/provinces/provinces.service';
import { Provinces } from 'app/shared/model/provinces.model';

describe('Component Tests', () => {
  describe('Provinces Management Update Component', () => {
    let comp: ProvincesUpdateComponent;
    let fixture: ComponentFixture<ProvincesUpdateComponent>;
    let service: ProvincesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ProvincesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProvincesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProvincesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProvincesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Provinces(123);
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
        const entity = new Provinces();
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
