import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { NiveauInstructionDetailComponent } from 'app/entities/niveau-instruction/niveau-instruction-detail.component';
import { NiveauInstruction } from 'app/shared/model/niveau-instruction.model';

describe('Component Tests', () => {
  describe('NiveauInstruction Management Detail Component', () => {
    let comp: NiveauInstructionDetailComponent;
    let fixture: ComponentFixture<NiveauInstructionDetailComponent>;
    const route = ({ data: of({ niveauInstruction: new NiveauInstruction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [NiveauInstructionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NiveauInstructionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NiveauInstructionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load niveauInstruction on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.niveauInstruction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
