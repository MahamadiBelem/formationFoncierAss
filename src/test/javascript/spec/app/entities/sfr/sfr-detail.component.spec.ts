import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SfrDetailComponent } from 'app/entities/sfr/sfr-detail.component';
import { Sfr } from 'app/shared/model/sfr.model';

describe('Component Tests', () => {
  describe('Sfr Management Detail Component', () => {
    let comp: SfrDetailComponent;
    let fixture: ComponentFixture<SfrDetailComponent>;
    const route = ({ data: of({ sfr: new Sfr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SfrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SfrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SfrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sfr on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sfr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
