import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { InstallationComponent } from 'app/entities/installation/installation.component';
import { InstallationService } from 'app/entities/installation/installation.service';
import { Installation } from 'app/shared/model/installation.model';

describe('Component Tests', () => {
  describe('Installation Management Component', () => {
    let comp: InstallationComponent;
    let fixture: ComponentFixture<InstallationComponent>;
    let service: InstallationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [InstallationComponent],
      })
        .overrideTemplate(InstallationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InstallationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InstallationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Installation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.installations && comp.installations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
