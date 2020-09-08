import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ActiviteInstallationUpdateComponent } from 'app/entities/activite-installation/activite-installation-update.component';
import { ActiviteInstallationService } from 'app/entities/activite-installation/activite-installation.service';
import { ActiviteInstallation } from 'app/shared/model/activite-installation.model';

describe('Component Tests', () => {
  describe('ActiviteInstallation Management Update Component', () => {
    let comp: ActiviteInstallationUpdateComponent;
    let fixture: ComponentFixture<ActiviteInstallationUpdateComponent>;
    let service: ActiviteInstallationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ActiviteInstallationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ActiviteInstallationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ActiviteInstallationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ActiviteInstallationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ActiviteInstallation(123);
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
        const entity = new ActiviteInstallation();
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
