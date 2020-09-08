import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { DomaineFormationUpdateComponent } from 'app/entities/domaine-formation/domaine-formation-update.component';
import { DomaineFormationService } from 'app/entities/domaine-formation/domaine-formation.service';
import { DomaineFormation } from 'app/shared/model/domaine-formation.model';

describe('Component Tests', () => {
  describe('DomaineFormation Management Update Component', () => {
    let comp: DomaineFormationUpdateComponent;
    let fixture: ComponentFixture<DomaineFormationUpdateComponent>;
    let service: DomaineFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [DomaineFormationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DomaineFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DomaineFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DomaineFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DomaineFormation(123);
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
        const entity = new DomaineFormation();
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
