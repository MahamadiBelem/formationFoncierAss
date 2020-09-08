import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeAmenagementComponent } from 'app/entities/type-amenagement/type-amenagement.component';
import { TypeAmenagementService } from 'app/entities/type-amenagement/type-amenagement.service';
import { TypeAmenagement } from 'app/shared/model/type-amenagement.model';

describe('Component Tests', () => {
  describe('TypeAmenagement Management Component', () => {
    let comp: TypeAmenagementComponent;
    let fixture: ComponentFixture<TypeAmenagementComponent>;
    let service: TypeAmenagementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeAmenagementComponent],
      })
        .overrideTemplate(TypeAmenagementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAmenagementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAmenagementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeAmenagement(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeAmenagements && comp.typeAmenagements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
