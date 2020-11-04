import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { DossierApfrDetailComponent } from 'app/entities/dossier-apfr/dossier-apfr-detail.component';
import { DossierApfr } from 'app/shared/model/dossier-apfr.model';

describe('Component Tests', () => {
  describe('DossierApfr Management Detail Component', () => {
    let comp: DossierApfrDetailComponent;
    let fixture: ComponentFixture<DossierApfrDetailComponent>;
    const route = ({ data: of({ dossierApfr: new DossierApfr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [DossierApfrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DossierApfrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DossierApfrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dossierApfr on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dossierApfr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
