import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { FormateurCentreComponent } from 'app/entities/formateur-centre/formateur-centre.component';
import { FormateurCentreService } from 'app/entities/formateur-centre/formateur-centre.service';
import { FormateurCentre } from 'app/shared/model/formateur-centre.model';

describe('Component Tests', () => {
  describe('FormateurCentre Management Component', () => {
    let comp: FormateurCentreComponent;
    let fixture: ComponentFixture<FormateurCentreComponent>;
    let service: FormateurCentreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormateurCentreComponent],
      })
        .overrideTemplate(FormateurCentreComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormateurCentreComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormateurCentreService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormateurCentre(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formateurCentres && comp.formateurCentres[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
