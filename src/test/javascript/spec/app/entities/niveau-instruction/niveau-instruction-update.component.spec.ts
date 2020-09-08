import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { NiveauInstructionUpdateComponent } from 'app/entities/niveau-instruction/niveau-instruction-update.component';
import { NiveauInstructionService } from 'app/entities/niveau-instruction/niveau-instruction.service';
import { NiveauInstruction } from 'app/shared/model/niveau-instruction.model';

describe('Component Tests', () => {
  describe('NiveauInstruction Management Update Component', () => {
    let comp: NiveauInstructionUpdateComponent;
    let fixture: ComponentFixture<NiveauInstructionUpdateComponent>;
    let service: NiveauInstructionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [NiveauInstructionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NiveauInstructionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NiveauInstructionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NiveauInstructionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NiveauInstruction(123);
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
        const entity = new NiveauInstruction();
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
