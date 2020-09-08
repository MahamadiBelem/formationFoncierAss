import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ApprenantesUpdateComponent } from 'app/entities/apprenantes/apprenantes-update.component';
import { ApprenantesService } from 'app/entities/apprenantes/apprenantes.service';
import { Apprenantes } from 'app/shared/model/apprenantes.model';

describe('Component Tests', () => {
  describe('Apprenantes Management Update Component', () => {
    let comp: ApprenantesUpdateComponent;
    let fixture: ComponentFixture<ApprenantesUpdateComponent>;
    let service: ApprenantesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprenantesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ApprenantesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApprenantesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprenantesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Apprenantes(123);
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
        const entity = new Apprenantes();
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
