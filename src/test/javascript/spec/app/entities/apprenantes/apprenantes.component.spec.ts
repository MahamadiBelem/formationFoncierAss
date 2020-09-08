import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ApprenantesComponent } from 'app/entities/apprenantes/apprenantes.component';
import { ApprenantesService } from 'app/entities/apprenantes/apprenantes.service';
import { Apprenantes } from 'app/shared/model/apprenantes.model';

describe('Component Tests', () => {
  describe('Apprenantes Management Component', () => {
    let comp: ApprenantesComponent;
    let fixture: ComponentFixture<ApprenantesComponent>;
    let service: ApprenantesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprenantesComponent],
      })
        .overrideTemplate(ApprenantesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApprenantesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprenantesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Apprenantes(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apprenantes && comp.apprenantes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
