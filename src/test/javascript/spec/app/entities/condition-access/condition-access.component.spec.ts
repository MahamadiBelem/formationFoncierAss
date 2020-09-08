import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { ConditionAccessComponent } from 'app/entities/condition-access/condition-access.component';
import { ConditionAccessService } from 'app/entities/condition-access/condition-access.service';
import { ConditionAccess } from 'app/shared/model/condition-access.model';

describe('Component Tests', () => {
  describe('ConditionAccess Management Component', () => {
    let comp: ConditionAccessComponent;
    let fixture: ComponentFixture<ConditionAccessComponent>;
    let service: ConditionAccessService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ConditionAccessComponent],
      })
        .overrideTemplate(ConditionAccessComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConditionAccessComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConditionAccessService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConditionAccess(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.conditionAccesses && comp.conditionAccesses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
