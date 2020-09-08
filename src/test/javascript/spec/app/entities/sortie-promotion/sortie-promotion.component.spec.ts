import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { SortiePromotionComponent } from 'app/entities/sortie-promotion/sortie-promotion.component';
import { SortiePromotionService } from 'app/entities/sortie-promotion/sortie-promotion.service';
import { SortiePromotion } from 'app/shared/model/sortie-promotion.model';

describe('Component Tests', () => {
  describe('SortiePromotion Management Component', () => {
    let comp: SortiePromotionComponent;
    let fixture: ComponentFixture<SortiePromotionComponent>;
    let service: SortiePromotionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SortiePromotionComponent],
      })
        .overrideTemplate(SortiePromotionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SortiePromotionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SortiePromotionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SortiePromotion(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sortiePromotions && comp.sortiePromotions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
