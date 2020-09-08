import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { NiveauRecrutementDetailComponent } from 'app/entities/niveau-recrutement/niveau-recrutement-detail.component';
import { NiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';

describe('Component Tests', () => {
  describe('NiveauRecrutement Management Detail Component', () => {
    let comp: NiveauRecrutementDetailComponent;
    let fixture: ComponentFixture<NiveauRecrutementDetailComponent>;
    const route = ({ data: of({ niveauRecrutement: new NiveauRecrutement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [NiveauRecrutementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NiveauRecrutementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NiveauRecrutementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load niveauRecrutement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.niveauRecrutement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
