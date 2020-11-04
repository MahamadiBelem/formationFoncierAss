import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeDemandeurUpdateComponent } from 'app/entities/type-demandeur/type-demandeur-update.component';
import { TypeDemandeurService } from 'app/entities/type-demandeur/type-demandeur.service';
import { TypeDemandeur } from 'app/shared/model/type-demandeur.model';

describe('Component Tests', () => {
  describe('TypeDemandeur Management Update Component', () => {
    let comp: TypeDemandeurUpdateComponent;
    let fixture: ComponentFixture<TypeDemandeurUpdateComponent>;
    let service: TypeDemandeurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeDemandeurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeDemandeurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeDemandeurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeDemandeurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeDemandeur(123);
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
        const entity = new TypeDemandeur();
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
