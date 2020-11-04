import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeActeComponent } from 'app/entities/type-acte/type-acte.component';
import { TypeActeService } from 'app/entities/type-acte/type-acte.service';
import { TypeActe } from 'app/shared/model/type-acte.model';

describe('Component Tests', () => {
  describe('TypeActe Management Component', () => {
    let comp: TypeActeComponent;
    let fixture: ComponentFixture<TypeActeComponent>;
    let service: TypeActeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeActeComponent],
      })
        .overrideTemplate(TypeActeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeActeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeActeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeActe(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeActes && comp.typeActes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
