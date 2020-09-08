import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { NiveauRecrutementComponent } from 'app/entities/niveau-recrutement/niveau-recrutement.component';
import { NiveauRecrutementService } from 'app/entities/niveau-recrutement/niveau-recrutement.service';
import { NiveauRecrutement } from 'app/shared/model/niveau-recrutement.model';

describe('Component Tests', () => {
  describe('NiveauRecrutement Management Component', () => {
    let comp: NiveauRecrutementComponent;
    let fixture: ComponentFixture<NiveauRecrutementComponent>;
    let service: NiveauRecrutementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [NiveauRecrutementComponent],
      })
        .overrideTemplate(NiveauRecrutementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NiveauRecrutementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NiveauRecrutementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NiveauRecrutement(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.niveauRecrutements && comp.niveauRecrutements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
