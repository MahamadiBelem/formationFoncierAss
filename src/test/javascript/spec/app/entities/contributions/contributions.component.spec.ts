import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ContributionsComponent } from 'app/entities/contributions/contributions.component';
import { ContributionsService } from 'app/entities/contributions/contributions.service';
import { Contributions } from 'app/shared/model/contributions.model';

describe('Component Tests', () => {
  describe('Contributions Management Component', () => {
    let comp: ContributionsComponent;
    let fixture: ComponentFixture<ContributionsComponent>;
    let service: ContributionsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ContributionsComponent],
      })
        .overrideTemplate(ContributionsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContributionsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContributionsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Contributions(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contributions && comp.contributions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
