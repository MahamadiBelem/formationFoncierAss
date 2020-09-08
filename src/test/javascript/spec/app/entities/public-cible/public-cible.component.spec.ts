import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { PublicCibleComponent } from 'app/entities/public-cible/public-cible.component';
import { PublicCibleService } from 'app/entities/public-cible/public-cible.service';
import { PublicCible } from 'app/shared/model/public-cible.model';

describe('Component Tests', () => {
  describe('PublicCible Management Component', () => {
    let comp: PublicCibleComponent;
    let fixture: ComponentFixture<PublicCibleComponent>;
    let service: PublicCibleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [PublicCibleComponent],
      })
        .overrideTemplate(PublicCibleComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PublicCibleComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PublicCibleService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PublicCible(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.publicCibles && comp.publicCibles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
