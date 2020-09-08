import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CentreFormationDetailComponent } from 'app/entities/centre-formation/centre-formation-detail.component';
import { CentreFormation } from 'app/shared/model/centre-formation.model';

describe('Component Tests', () => {
  describe('CentreFormation Management Detail Component', () => {
    let comp: CentreFormationDetailComponent;
    let fixture: ComponentFixture<CentreFormationDetailComponent>;
    const route = ({ data: of({ centreFormation: new CentreFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CentreFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CentreFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CentreFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load centreFormation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.centreFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
