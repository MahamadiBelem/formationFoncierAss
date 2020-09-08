import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { SpecialitesComponent } from 'app/entities/specialites/specialites.component';
import { SpecialitesService } from 'app/entities/specialites/specialites.service';
import { Specialites } from 'app/shared/model/specialites.model';

describe('Component Tests', () => {
  describe('Specialites Management Component', () => {
    let comp: SpecialitesComponent;
    let fixture: ComponentFixture<SpecialitesComponent>;
    let service: SpecialitesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SpecialitesComponent],
      })
        .overrideTemplate(SpecialitesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecialitesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecialitesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Specialites(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.specialites && comp.specialites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
