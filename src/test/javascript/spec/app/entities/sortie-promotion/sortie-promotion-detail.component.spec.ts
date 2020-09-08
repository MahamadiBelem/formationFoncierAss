import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SortiePromotionDetailComponent } from 'app/entities/sortie-promotion/sortie-promotion-detail.component';
import { SortiePromotion } from 'app/shared/model/sortie-promotion.model';

describe('Component Tests', () => {
  describe('SortiePromotion Management Detail Component', () => {
    let comp: SortiePromotionDetailComponent;
    let fixture: ComponentFixture<SortiePromotionDetailComponent>;
    const route = ({ data: of({ sortiePromotion: new SortiePromotion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SortiePromotionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SortiePromotionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SortiePromotionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sortiePromotion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sortiePromotion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
