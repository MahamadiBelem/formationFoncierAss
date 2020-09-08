import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { InfrastructureUpdateComponent } from 'app/entities/infrastructure/infrastructure-update.component';
import { InfrastructureService } from 'app/entities/infrastructure/infrastructure.service';
import { Infrastructure } from 'app/shared/model/infrastructure.model';

describe('Component Tests', () => {
  describe('Infrastructure Management Update Component', () => {
    let comp: InfrastructureUpdateComponent;
    let fixture: ComponentFixture<InfrastructureUpdateComponent>;
    let service: InfrastructureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [InfrastructureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InfrastructureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InfrastructureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InfrastructureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Infrastructure(123);
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
        const entity = new Infrastructure();
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
