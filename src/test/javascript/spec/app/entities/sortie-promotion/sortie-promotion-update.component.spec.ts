import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SortiePromotionUpdateComponent } from 'app/entities/sortie-promotion/sortie-promotion-update.component';
import { SortiePromotionService } from 'app/entities/sortie-promotion/sortie-promotion.service';
import { SortiePromotion } from 'app/shared/model/sortie-promotion.model';

describe('Component Tests', () => {
  describe('SortiePromotion Management Update Component', () => {
    let comp: SortiePromotionUpdateComponent;
    let fixture: ComponentFixture<SortiePromotionUpdateComponent>;
    let service: SortiePromotionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SortiePromotionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SortiePromotionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SortiePromotionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SortiePromotionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SortiePromotion(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SortiePromotion();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
