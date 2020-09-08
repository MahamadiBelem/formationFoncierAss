import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ActiviteInstallationComponent } from 'app/entities/activite-installation/activite-installation.component';
import { ActiviteInstallationService } from 'app/entities/activite-installation/activite-installation.service';
import { ActiviteInstallation } from 'app/shared/model/activite-installation.model';

describe('Component Tests', () => {
  describe('ActiviteInstallation Management Component', () => {
    let comp: ActiviteInstallationComponent;
    let fixture: ComponentFixture<ActiviteInstallationComponent>;
    let service: ActiviteInstallationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ActiviteInstallationComponent],
      })
        .overrideTemplate(ActiviteInstallationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ActiviteInstallationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ActiviteInstallationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ActiviteInstallation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.activiteInstallations && comp.activiteInstallations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
