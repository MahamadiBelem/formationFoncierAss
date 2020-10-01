import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypecandidatUpdateComponent } from 'app/entities/typecandidat/typecandidat-update.component';
import { TypecandidatService } from 'app/entities/typecandidat/typecandidat.service';
import { Typecandidat } from 'app/shared/model/typecandidat.model';

describe('Component Tests', () => {
  describe('Typecandidat Management Update Component', () => {
    let comp: TypecandidatUpdateComponent;
    let fixture: ComponentFixture<TypecandidatUpdateComponent>;
    let service: TypecandidatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypecandidatUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypecandidatUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypecandidatUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypecandidatService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Typecandidat(123);
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
        const entity = new Typecandidat();
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
