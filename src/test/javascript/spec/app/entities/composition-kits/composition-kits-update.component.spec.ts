import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CompositionKitsUpdateComponent } from 'app/entities/composition-kits/composition-kits-update.component';
import { CompositionKitsService } from 'app/entities/composition-kits/composition-kits.service';
import { CompositionKits } from 'app/shared/model/composition-kits.model';

describe('Component Tests', () => {
  describe('CompositionKits Management Update Component', () => {
    let comp: CompositionKitsUpdateComponent;
    let fixture: ComponentFixture<CompositionKitsUpdateComponent>;
    let service: CompositionKitsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CompositionKitsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CompositionKitsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompositionKitsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompositionKitsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CompositionKits(123);
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
        const entity = new CompositionKits();
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
