import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeInfratructureComponent } from 'app/entities/type-infratructure/type-infratructure.component';
import { TypeInfratructureService } from 'app/entities/type-infratructure/type-infratructure.service';
import { TypeInfratructure } from 'app/shared/model/type-infratructure.model';

describe('Component Tests', () => {
  describe('TypeInfratructure Management Component', () => {
    let comp: TypeInfratructureComponent;
    let fixture: ComponentFixture<TypeInfratructureComponent>;
    let service: TypeInfratructureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeInfratructureComponent],
      })
        .overrideTemplate(TypeInfratructureComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeInfratructureComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeInfratructureService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeInfratructure(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeInfratructures && comp.typeInfratructures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
