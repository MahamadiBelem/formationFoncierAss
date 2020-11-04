import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationSFRUpdateComponent } from 'app/entities/formation-sfr/formation-sfr-update.component';
import { FormationSFRService } from 'app/entities/formation-sfr/formation-sfr.service';
import { FormationSFR } from 'app/shared/model/formation-sfr.model';

describe('Component Tests', () => {
  describe('FormationSFR Management Update Component', () => {
    let comp: FormationSFRUpdateComponent;
    let fixture: ComponentFixture<FormationSFRUpdateComponent>;
    let service: FormationSFRService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationSFRUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FormationSFRUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormationSFRUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormationSFRService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormationSFR(123);
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
        const entity = new FormationSFR();
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
