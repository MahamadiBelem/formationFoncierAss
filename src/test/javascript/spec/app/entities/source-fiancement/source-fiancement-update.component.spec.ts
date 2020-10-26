import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SourceFiancementUpdateComponent } from 'app/entities/source-fiancement/source-fiancement-update.component';
import { SourceFiancementService } from 'app/entities/source-fiancement/source-fiancement.service';
import { SourceFiancement } from 'app/shared/model/source-fiancement.model';

describe('Component Tests', () => {
  describe('SourceFiancement Management Update Component', () => {
    let comp: SourceFiancementUpdateComponent;
    let fixture: ComponentFixture<SourceFiancementUpdateComponent>;
    let service: SourceFiancementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SourceFiancementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SourceFiancementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SourceFiancementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SourceFiancementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SourceFiancement(123);
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
        const entity = new SourceFiancement();
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
