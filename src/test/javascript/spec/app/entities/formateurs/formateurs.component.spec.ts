import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { FormateursComponent } from 'app/entities/formateurs/formateurs.component';
import { FormateursService } from 'app/entities/formateurs/formateurs.service';
import { Formateurs } from 'app/shared/model/formateurs.model';

describe('Component Tests', () => {
  describe('Formateurs Management Component', () => {
    let comp: FormateursComponent;
    let fixture: ComponentFixture<FormateursComponent>;
    let service: FormateursService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormateursComponent],
      })
        .overrideTemplate(FormateursComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormateursComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormateursService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Formateurs(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formateurs && comp.formateurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
