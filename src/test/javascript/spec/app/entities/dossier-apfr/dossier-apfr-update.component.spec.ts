import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { DossierApfrUpdateComponent } from 'app/entities/dossier-apfr/dossier-apfr-update.component';
import { DossierApfrService } from 'app/entities/dossier-apfr/dossier-apfr.service';
import { DossierApfr } from 'app/shared/model/dossier-apfr.model';

describe('Component Tests', () => {
  describe('DossierApfr Management Update Component', () => {
    let comp: DossierApfrUpdateComponent;
    let fixture: ComponentFixture<DossierApfrUpdateComponent>;
    let service: DossierApfrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [DossierApfrUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DossierApfrUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DossierApfrUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DossierApfrService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DossierApfr(123);
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
        const entity = new DossierApfr();
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
