import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ProfilPersonnelUpdateComponent } from 'app/entities/profil-personnel/profil-personnel-update.component';
import { ProfilPersonnelService } from 'app/entities/profil-personnel/profil-personnel.service';
import { ProfilPersonnel } from 'app/shared/model/profil-personnel.model';

describe('Component Tests', () => {
  describe('ProfilPersonnel Management Update Component', () => {
    let comp: ProfilPersonnelUpdateComponent;
    let fixture: ComponentFixture<ProfilPersonnelUpdateComponent>;
    let service: ProfilPersonnelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ProfilPersonnelUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProfilPersonnelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProfilPersonnelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProfilPersonnelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProfilPersonnel(123);
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
        const entity = new ProfilPersonnel();
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
