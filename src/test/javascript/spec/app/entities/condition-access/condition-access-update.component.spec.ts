import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ConditionAccessUpdateComponent } from 'app/entities/condition-access/condition-access-update.component';
import { ConditionAccessService } from 'app/entities/condition-access/condition-access.service';
import { ConditionAccess } from 'app/shared/model/condition-access.model';

describe('Component Tests', () => {
  describe('ConditionAccess Management Update Component', () => {
    let comp: ConditionAccessUpdateComponent;
    let fixture: ComponentFixture<ConditionAccessUpdateComponent>;
    let service: ConditionAccessService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ConditionAccessUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConditionAccessUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConditionAccessUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConditionAccessService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConditionAccess(123);
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
        const entity = new ConditionAccess();
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
