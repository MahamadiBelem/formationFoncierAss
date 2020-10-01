import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { TypecandidatComponent } from 'app/entities/typecandidat/typecandidat.component';
import { TypecandidatService } from 'app/entities/typecandidat/typecandidat.service';
import { Typecandidat } from 'app/shared/model/typecandidat.model';

describe('Component Tests', () => {
  describe('Typecandidat Management Component', () => {
    let comp: TypecandidatComponent;
    let fixture: ComponentFixture<TypecandidatComponent>;
    let service: TypecandidatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypecandidatComponent],
      })
        .overrideTemplate(TypecandidatComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypecandidatComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypecandidatService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Typecandidat(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typecandidats && comp.typecandidats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
