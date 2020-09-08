import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { NiveauRecrutementUpdateComponent } from 'app/entities/niveau-recrutement/niveau-recrutement-update.component';
import { NiveauRecrutementService } from 'app/entities/niveau-recrutement/niveau-recrutement.service';
import { NiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';

describe('Component Tests', () => {
  describe('NiveauRecrutement Management Update Component', () => {
    let comp: NiveauRecrutementUpdateComponent;
    let fixture: ComponentFixture<NiveauRecrutementUpdateComponent>;
    let service: NiveauRecrutementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [NiveauRecrutementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NiveauRecrutementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NiveauRecrutementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NiveauRecrutementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NiveauRecrutement(123);
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
        const entity = new NiveauRecrutement();
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
