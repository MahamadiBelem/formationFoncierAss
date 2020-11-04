import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CaracteristiqueSfrDetailComponent } from 'app/entities/caracteristique-sfr/caracteristique-sfr-detail.component';
import { CaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';

describe('Component Tests', () => {
  describe('CaracteristiqueSfr Management Detail Component', () => {
    let comp: CaracteristiqueSfrDetailComponent;
    let fixture: ComponentFixture<CaracteristiqueSfrDetailComponent>;
    const route = ({ data: of({ caracteristiqueSfr: new CaracteristiqueSfr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CaracteristiqueSfrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CaracteristiqueSfrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaracteristiqueSfrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load caracteristiqueSfr on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.caracteristiqueSfr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
