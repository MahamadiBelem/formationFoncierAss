import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { VillagesComponent } from 'app/entities/villages/villages.component';
import { VillagesService } from 'app/entities/villages/villages.service';
import { Villages } from 'app/shared/model/villages.model';

describe('Component Tests', () => {
  describe('Villages Management Component', () => {
    let comp: VillagesComponent;
    let fixture: ComponentFixture<VillagesComponent>;
    let service: VillagesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [VillagesComponent],
      })
        .overrideTemplate(VillagesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VillagesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VillagesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Villages(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.villages && comp.villages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
