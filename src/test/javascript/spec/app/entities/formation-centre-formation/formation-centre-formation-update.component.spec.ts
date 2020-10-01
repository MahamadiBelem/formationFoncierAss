import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationCentreFormationUpdateComponent } from 'app/entities/formation-centre-formation/formation-centre-formation-update.component';
import { FormationCentreFormationService } from 'app/entities/formation-centre-formation/formation-centre-formation.service';
import { FormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';

describe('Component Tests', () => {
  describe('FormationCentreFormation Management Update Component', () => {
    let comp: FormationCentreFormationUpdateComponent;
    let fixture: ComponentFixture<FormationCentreFormationUpdateComponent>;
    let service: FormationCentreFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationCentreFormationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FormationCentreFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormationCentreFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormationCentreFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormationCentreFormation(123);
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
        const entity = new FormationCentreFormation();
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
