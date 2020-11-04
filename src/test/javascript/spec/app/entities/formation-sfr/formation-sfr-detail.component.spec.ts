import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationSFRDetailComponent } from 'app/entities/formation-sfr/formation-sfr-detail.component';
import { FormationSFR } from 'app/shared/model/formation-sfr.model';

describe('Component Tests', () => {
  describe('FormationSFR Management Detail Component', () => {
    let comp: FormationSFRDetailComponent;
    let fixture: ComponentFixture<FormationSFRDetailComponent>;
    const route = ({ data: of({ formationSFR: new FormationSFR(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationSFRDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormationSFRDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormationSFRDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formationSFR on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formationSFR).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
