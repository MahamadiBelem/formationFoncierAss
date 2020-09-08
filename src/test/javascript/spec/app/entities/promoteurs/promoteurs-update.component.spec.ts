import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { PromoteursUpdateComponent } from 'app/entities/promoteurs/promoteurs-update.component';
import { PromoteursService } from 'app/entities/promoteurs/promoteurs.service';
import { Promoteurs } from 'app/shared/model/promoteurs.model';

describe('Component Tests', () => {
  describe('Promoteurs Management Update Component', () => {
    let comp: PromoteursUpdateComponent;
    let fixture: ComponentFixture<PromoteursUpdateComponent>;
    let service: PromoteursService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [PromoteursUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PromoteursUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromoteursUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromoteursService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Promoteurs(123);
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
        const entity = new Promoteurs();
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
