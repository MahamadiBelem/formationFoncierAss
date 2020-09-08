import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { PublicCibleUpdateComponent } from 'app/entities/public-cible/public-cible-update.component';
import { PublicCibleService } from 'app/entities/public-cible/public-cible.service';
import { PublicCible } from 'app/shared/model/public-cible.model';

describe('Component Tests', () => {
  describe('PublicCible Management Update Component', () => {
    let comp: PublicCibleUpdateComponent;
    let fixture: ComponentFixture<PublicCibleUpdateComponent>;
    let service: PublicCibleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [PublicCibleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PublicCibleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PublicCibleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PublicCibleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PublicCible(123);
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
        const entity = new PublicCible();
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
