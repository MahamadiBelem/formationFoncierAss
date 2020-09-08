import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeInfratructureUpdateComponent } from 'app/entities/type-infratructure/type-infratructure-update.component';
import { TypeInfratructureService } from 'app/entities/type-infratructure/type-infratructure.service';
import { TypeInfratructure } from 'app/shared/model/type-infratructure.model';

describe('Component Tests', () => {
  describe('TypeInfratructure Management Update Component', () => {
    let comp: TypeInfratructureUpdateComponent;
    let fixture: ComponentFixture<TypeInfratructureUpdateComponent>;
    let service: TypeInfratructureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeInfratructureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeInfratructureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeInfratructureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeInfratructureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeInfratructure(123);
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
        const entity = new TypeInfratructure();
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
