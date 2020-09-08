import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { PromoteursComponent } from 'app/entities/promoteurs/promoteurs.component';
import { PromoteursService } from 'app/entities/promoteurs/promoteurs.service';
import { Promoteurs } from 'app/shared/model/promoteurs.model';

describe('Component Tests', () => {
  describe('Promoteurs Management Component', () => {
    let comp: PromoteursComponent;
    let fixture: ComponentFixture<PromoteursComponent>;
    let service: PromoteursService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [PromoteursComponent],
      })
        .overrideTemplate(PromoteursComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PromoteursComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PromoteursService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Promoteurs(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.promoteurs && comp.promoteurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
