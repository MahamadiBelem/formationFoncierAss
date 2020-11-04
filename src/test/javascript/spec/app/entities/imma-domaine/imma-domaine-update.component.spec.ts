import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ImmaDomaineUpdateComponent } from 'app/entities/imma-domaine/imma-domaine-update.component';
import { ImmaDomaineService } from 'app/entities/imma-domaine/imma-domaine.service';
import { ImmaDomaine } from 'app/shared/model/imma-domaine.model';

describe('Component Tests', () => {
  describe('ImmaDomaine Management Update Component', () => {
    let comp: ImmaDomaineUpdateComponent;
    let fixture: ComponentFixture<ImmaDomaineUpdateComponent>;
    let service: ImmaDomaineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ImmaDomaineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ImmaDomaineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImmaDomaineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImmaDomaineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ImmaDomaine(123);
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
        const entity = new ImmaDomaine();
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
