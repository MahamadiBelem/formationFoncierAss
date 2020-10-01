import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormateurCentreDetailComponent } from 'app/entities/formateur-centre/formateur-centre-detail.component';
import { FormateurCentre } from 'app/shared/model/formateur-centre.model';

describe('Component Tests', () => {
  describe('FormateurCentre Management Detail Component', () => {
    let comp: FormateurCentreDetailComponent;
    let fixture: ComponentFixture<FormateurCentreDetailComponent>;
    const route = ({ data: of({ formateurCentre: new FormateurCentre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormateurCentreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormateurCentreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormateurCentreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formateurCentre on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formateurCentre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
