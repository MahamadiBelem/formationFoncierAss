import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { RegimeComponent } from 'app/entities/regime/regime.component';
import { RegimeService } from 'app/entities/regime/regime.service';
import { Regime } from 'app/shared/model/regime.model';

describe('Component Tests', () => {
  describe('Regime Management Component', () => {
    let comp: RegimeComponent;
    let fixture: ComponentFixture<RegimeComponent>;
    let service: RegimeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [RegimeComponent],
      })
        .overrideTemplate(RegimeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RegimeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RegimeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Regime(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.regimes && comp.regimes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
