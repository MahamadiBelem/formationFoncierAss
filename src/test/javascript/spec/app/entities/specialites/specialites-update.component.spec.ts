import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SpecialitesUpdateComponent } from 'app/entities/specialites/specialites-update.component';
import { SpecialitesService } from 'app/entities/specialites/specialites.service';
import { Specialites } from 'app/shared/model/specialites.model';

describe('Component Tests', () => {
  describe('Specialites Management Update Component', () => {
    let comp: SpecialitesUpdateComponent;
    let fixture: ComponentFixture<SpecialitesUpdateComponent>;
    let service: SpecialitesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SpecialitesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SpecialitesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecialitesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecialitesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Specialites(123);
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
        const entity = new Specialites();
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
