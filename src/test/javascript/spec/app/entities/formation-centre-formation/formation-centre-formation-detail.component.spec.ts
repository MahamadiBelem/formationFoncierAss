import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationCentreFormationDetailComponent } from 'app/entities/formation-centre-formation/formation-centre-formation-detail.component';
import { FormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';

describe('Component Tests', () => {
  describe('FormationCentreFormation Management Detail Component', () => {
    let comp: FormationCentreFormationDetailComponent;
    let fixture: ComponentFixture<FormationCentreFormationDetailComponent>;
    const route = ({ data: of({ formationCentreFormation: new FormationCentreFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationCentreFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormationCentreFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormationCentreFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formationCentreFormation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formationCentreFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
