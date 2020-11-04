import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SfrUpdateComponent } from 'app/entities/sfr/sfr-update.component';
import { SfrService } from 'app/entities/sfr/sfr.service';
import { Sfr } from 'app/shared/model/sfr.model';

describe('Component Tests', () => {
  describe('Sfr Management Update Component', () => {
    let comp: SfrUpdateComponent;
    let fixture: ComponentFixture<SfrUpdateComponent>;
    let service: SfrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SfrUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SfrUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SfrUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SfrService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Sfr(123);
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
        const entity = new Sfr();
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
