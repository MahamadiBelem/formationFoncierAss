import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ProfilPersonnelComponent } from 'app/entities/profil-personnel/profil-personnel.component';
import { ProfilPersonnelService } from 'app/entities/profil-personnel/profil-personnel.service';
import { ProfilPersonnel } from 'app/shared/model/profil-personnel.model';

describe('Component Tests', () => {
  describe('ProfilPersonnel Management Component', () => {
    let comp: ProfilPersonnelComponent;
    let fixture: ComponentFixture<ProfilPersonnelComponent>;
    let service: ProfilPersonnelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ProfilPersonnelComponent],
      })
        .overrideTemplate(ProfilPersonnelComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProfilPersonnelComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProfilPersonnelService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProfilPersonnel(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.profilPersonnels && comp.profilPersonnels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
