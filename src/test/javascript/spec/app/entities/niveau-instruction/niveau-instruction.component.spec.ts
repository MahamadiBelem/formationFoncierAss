import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { NiveauInstructionComponent } from 'app/entities/niveau-instruction/niveau-instruction.component';
import { NiveauInstructionService } from 'app/entities/niveau-instruction/niveau-instruction.service';
import { NiveauInstruction } from 'app/shared/model/niveau-instruction.model';

describe('Component Tests', () => {
  describe('NiveauInstruction Management Component', () => {
    let comp: NiveauInstructionComponent;
    let fixture: ComponentFixture<NiveauInstructionComponent>;
    let service: NiveauInstructionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [NiveauInstructionComponent],
      })
        .overrideTemplate(NiveauInstructionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NiveauInstructionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NiveauInstructionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NiveauInstruction(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.niveauInstructions && comp.niveauInstructions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
