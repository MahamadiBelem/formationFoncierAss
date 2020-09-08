import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ApprochePedagogiqueComponent } from 'app/entities/approche-pedagogique/approche-pedagogique.component';
import { ApprochePedagogiqueService } from 'app/entities/approche-pedagogique/approche-pedagogique.service';
import { ApprochePedagogique } from 'app/shared/model/approche-pedagogique.model';

describe('Component Tests', () => {
  describe('ApprochePedagogique Management Component', () => {
    let comp: ApprochePedagogiqueComponent;
    let fixture: ComponentFixture<ApprochePedagogiqueComponent>;
    let service: ApprochePedagogiqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprochePedagogiqueComponent],
      })
        .overrideTemplate(ApprochePedagogiqueComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApprochePedagogiqueComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprochePedagogiqueService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApprochePedagogique(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.approchePedagogiques && comp.approchePedagogiques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
