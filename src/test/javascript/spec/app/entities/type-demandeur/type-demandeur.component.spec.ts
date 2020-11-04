import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeDemandeurComponent } from 'app/entities/type-demandeur/type-demandeur.component';
import { TypeDemandeurService } from 'app/entities/type-demandeur/type-demandeur.service';
import { TypeDemandeur } from 'app/shared/model/type-demandeur.model';

describe('Component Tests', () => {
  describe('TypeDemandeur Management Component', () => {
    let comp: TypeDemandeurComponent;
    let fixture: ComponentFixture<TypeDemandeurComponent>;
    let service: TypeDemandeurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeDemandeurComponent],
      })
        .overrideTemplate(TypeDemandeurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeDemandeurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeDemandeurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeDemandeur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeDemandeurs && comp.typeDemandeurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
