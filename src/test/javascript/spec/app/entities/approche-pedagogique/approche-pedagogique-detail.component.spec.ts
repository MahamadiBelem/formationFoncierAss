import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ApprochePedagogiqueDetailComponent } from 'app/entities/approche-pedagogique/approche-pedagogique-detail.component';
import { ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';

describe('Component Tests', () => {
  describe('ApprochePedagogique Management Detail Component', () => {
    let comp: ApprochePedagogiqueDetailComponent;
    let fixture: ComponentFixture<ApprochePedagogiqueDetailComponent>;
    const route = ({ data: of({ approchePedagogique: new ApprochePedagogique(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprochePedagogiqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ApprochePedagogiqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApprochePedagogiqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load approchePedagogique on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.approchePedagogique).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
