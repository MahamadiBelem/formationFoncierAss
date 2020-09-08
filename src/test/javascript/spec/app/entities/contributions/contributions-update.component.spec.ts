import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ContributionsUpdateComponent } from 'app/entities/contributions/contributions-update.component';
import { ContributionsService } from 'app/entities/contributions/contributions.service';
import { Contributions } from 'app/shared/model/contributions.model';

describe('Component Tests', () => {
  describe('Contributions Management Update Component', () => {
    let comp: ContributionsUpdateComponent;
    let fixture: ComponentFixture<ContributionsUpdateComponent>;
    let service: ContributionsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ContributionsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ContributionsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContributionsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContributionsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Contributions(123);
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
        const entity = new Contributions();
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
