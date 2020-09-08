import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { DomaineFormationDetailComponent } from 'app/entities/domaine-formation/domaine-formation-detail.component';
import { DomaineFormation } from 'app/shared/model/domaine-formation.model';

describe('Component Tests', () => {
  describe('DomaineFormation Management Detail Component', () => {
    let comp: DomaineFormationDetailComponent;
    let fixture: ComponentFixture<DomaineFormationDetailComponent>;
    const route = ({ data: of({ domaineFormation: new DomaineFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [DomaineFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DomaineFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DomaineFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load domaineFormation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.domaineFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
