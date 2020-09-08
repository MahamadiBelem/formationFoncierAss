import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { PublicCibleDetailComponent } from 'app/entities/public-cible/public-cible-detail.component';
import { PublicCible } from 'app/shared/model/public-cible.model';

describe('Component Tests', () => {
  describe('PublicCible Management Detail Component', () => {
    let comp: PublicCibleDetailComponent;
    let fixture: ComponentFixture<PublicCibleDetailComponent>;
    const route = ({ data: of({ publicCible: new PublicCible(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [PublicCibleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PublicCibleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PublicCibleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load publicCible on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.publicCible).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
