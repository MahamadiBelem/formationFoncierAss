import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ProvincesComponent } from 'app/entities/provinces/provinces.component';
import { ProvincesService } from 'app/entities/provinces/provinces.service';
import { Provinces } from 'app/shared/model/provinces.model';

describe('Component Tests', () => {
  describe('Provinces Management Component', () => {
    let comp: ProvincesComponent;
    let fixture: ComponentFixture<ProvincesComponent>;
    let service: ProvincesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ProvincesComponent],
      })
        .overrideTemplate(ProvincesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProvincesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProvincesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Provinces(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.provinces && comp.provinces[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
