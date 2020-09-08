import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CentreFormationUpdateComponent } from 'app/entities/centre-formation/centre-formation-update.component';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';
import { CentreFormation } from 'app/shared/model/centre-formation.model';

describe('Component Tests', () => {
  describe('CentreFormation Management Update Component', () => {
    let comp: CentreFormationUpdateComponent;
    let fixture: ComponentFixture<CentreFormationUpdateComponent>;
    let service: CentreFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CentreFormationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CentreFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CentreFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CentreFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CentreFormation(123);
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
        const entity = new CentreFormation();
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
