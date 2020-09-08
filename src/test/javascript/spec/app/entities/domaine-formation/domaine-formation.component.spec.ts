import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { DomaineFormationComponent } from 'app/entities/domaine-formation/domaine-formation.component';
import { DomaineFormationService } from 'app/entities/domaine-formation/domaine-formation.service';
import { DomaineFormation } from 'app/shared/model/domaine-formation.model';

describe('Component Tests', () => {
  describe('DomaineFormation Management Component', () => {
    let comp: DomaineFormationComponent;
    let fixture: ComponentFixture<DomaineFormationComponent>;
    let service: DomaineFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [DomaineFormationComponent],
      })
        .overrideTemplate(DomaineFormationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DomaineFormationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DomaineFormationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DomaineFormation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.domaineFormations && comp.domaineFormations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
