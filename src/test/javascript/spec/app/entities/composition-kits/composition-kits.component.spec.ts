import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { CompositionKitsComponent } from 'app/entities/composition-kits/composition-kits.component';
import { CompositionKitsService } from 'app/entities/composition-kits/composition-kits.service';
import { CompositionKits } from 'app/shared/model/composition-kits.model';

describe('Component Tests', () => {
  describe('CompositionKits Management Component', () => {
    let comp: CompositionKitsComponent;
    let fixture: ComponentFixture<CompositionKitsComponent>;
    let service: CompositionKitsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CompositionKitsComponent],
      })
        .overrideTemplate(CompositionKitsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompositionKitsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompositionKitsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CompositionKits(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.compositionKits && comp.compositionKits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
