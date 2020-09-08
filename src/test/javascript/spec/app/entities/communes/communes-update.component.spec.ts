import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CommunesUpdateComponent } from 'app/entities/communes/communes-update.component';
import { CommunesService } from 'app/entities/communes/communes.service';
import { Communes } from 'app/shared/model/communes.model';

describe('Component Tests', () => {
  describe('Communes Management Update Component', () => {
    let comp: CommunesUpdateComponent;
    let fixture: ComponentFixture<CommunesUpdateComponent>;
    let service: CommunesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CommunesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommunesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommunesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommunesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Communes(123);
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
        const entity = new Communes();
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
