import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationCentreFormationComponent } from 'app/entities/formation-centre-formation/formation-centre-formation.component';
import { FormationCentreFormationService } from 'app/entities/formation-centre-formation/formation-centre-formation.service';
import { FormationCentreFormation } from 'app/shared/model/formation-centre-formation.model';

describe('Component Tests', () => {
  describe('FormationCentreFormation Management Component', () => {
    let comp: FormationCentreFormationComponent;
    let fixture: ComponentFixture<FormationCentreFormationComponent>;
    let service: FormationCentreFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationCentreFormationComponent],
      })
        .overrideTemplate(FormationCentreFormationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormationCentreFormationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormationCentreFormationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormationCentreFormation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formationCentreFormations && comp.formationCentreFormations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
