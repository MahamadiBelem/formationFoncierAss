import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { VillagesUpdateComponent } from 'app/entities/villages/villages-update.component';
import { VillagesService } from 'app/entities/villages/villages.service';
import { Villages } from 'app/shared/model/villages.model';

describe('Component Tests', () => {
  describe('Villages Management Update Component', () => {
    let comp: VillagesUpdateComponent;
    let fixture: ComponentFixture<VillagesUpdateComponent>;
    let service: VillagesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [VillagesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VillagesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VillagesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VillagesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Villages(123);
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
        const entity = new Villages();
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
