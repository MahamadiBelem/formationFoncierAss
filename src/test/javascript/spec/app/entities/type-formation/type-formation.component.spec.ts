import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeFormationComponent } from 'app/entities/type-formation/type-formation.component';
import { TypeFormationService } from 'app/entities/type-formation/type-formation.service';
import { TypeFormation } from 'app/shared/model/type-formation.model';

describe('Component Tests', () => {
  describe('TypeFormation Management Component', () => {
    let comp: TypeFormationComponent;
    let fixture: ComponentFixture<TypeFormationComponent>;
    let service: TypeFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeFormationComponent],
      })
        .overrideTemplate(TypeFormationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeFormationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeFormationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeFormation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeFormations && comp.typeFormations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
