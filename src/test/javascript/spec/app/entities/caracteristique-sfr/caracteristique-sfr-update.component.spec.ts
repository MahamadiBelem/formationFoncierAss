import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CaracteristiqueSfrUpdateComponent } from 'app/entities/caracteristique-sfr/caracteristique-sfr-update.component';
import { CaracteristiqueSfrService } from 'app/entities/caracteristique-sfr/caracteristique-sfr.service';
import { CaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';

describe('Component Tests', () => {
  describe('CaracteristiqueSfr Management Update Component', () => {
    let comp: CaracteristiqueSfrUpdateComponent;
    let fixture: ComponentFixture<CaracteristiqueSfrUpdateComponent>;
    let service: CaracteristiqueSfrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CaracteristiqueSfrUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CaracteristiqueSfrUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaracteristiqueSfrUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaracteristiqueSfrService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaracteristiqueSfr(123);
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
        const entity = new CaracteristiqueSfr();
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
