import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { PromoteursDetailComponent } from 'app/entities/promoteurs/promoteurs-detail.component';
import { Promoteurs } from 'app/shared/model/promoteurs.model';

describe('Component Tests', () => {
  describe('Promoteurs Management Detail Component', () => {
    let comp: PromoteursDetailComponent;
    let fixture: ComponentFixture<PromoteursDetailComponent>;
    const route = ({ data: of({ promoteurs: new Promoteurs(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [PromoteursDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PromoteursDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PromoteursDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load promoteurs on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.promoteurs).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
