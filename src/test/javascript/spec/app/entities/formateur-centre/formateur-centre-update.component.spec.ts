import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormateurCentreUpdateComponent } from 'app/entities/formateur-centre/formateur-centre-update.component';
import { FormateurCentreService } from 'app/entities/formateur-centre/formateur-centre.service';
import { FormateurCentre } from 'app/shared/model/formateur-centre.model';

describe('Component Tests', () => {
  describe('FormateurCentre Management Update Component', () => {
    let comp: FormateurCentreUpdateComponent;
    let fixture: ComponentFixture<FormateurCentreUpdateComponent>;
    let service: FormateurCentreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormateurCentreUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FormateurCentreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormateurCentreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormateurCentreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormateurCentre(123);
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
        const entity = new FormateurCentre();
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
