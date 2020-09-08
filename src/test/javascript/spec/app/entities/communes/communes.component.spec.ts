import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { CommunesComponent } from 'app/entities/communes/communes.component';
import { CommunesService } from 'app/entities/communes/communes.service';
import { Communes } from 'app/shared/model/communes.model';

describe('Component Tests', () => {
  describe('Communes Management Component', () => {
    let comp: CommunesComponent;
    let fixture: ComponentFixture<CommunesComponent>;
    let service: CommunesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CommunesComponent],
      })
        .overrideTemplate(CommunesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommunesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommunesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Communes(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.communes && comp.communes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
