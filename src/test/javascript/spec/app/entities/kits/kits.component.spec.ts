import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { KitsComponent } from 'app/entities/kits/kits.component';
import { KitsService } from 'app/entities/kits/kits.service';
import { Kits } from 'app/shared/model/kits.model';

describe('Component Tests', () => {
  describe('Kits Management Component', () => {
    let comp: KitsComponent;
    let fixture: ComponentFixture<KitsComponent>;
    let service: KitsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [KitsComponent],
      })
        .overrideTemplate(KitsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(KitsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(KitsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Kits(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.kits && comp.kits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
