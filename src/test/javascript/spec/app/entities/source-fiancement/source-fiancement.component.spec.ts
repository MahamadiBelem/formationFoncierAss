import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionFormationTestModule } from '../../../test.module';
import { SourceFiancementComponent } from 'app/entities/source-fiancement/source-fiancement.component';
import { SourceFiancementService } from 'app/entities/source-fiancement/source-fiancement.service';
import { SourceFiancement } from 'app/shared/model/source-fiancement.model';

describe('Component Tests', () => {
  describe('SourceFiancement Management Component', () => {
    let comp: SourceFiancementComponent;
    let fixture: ComponentFixture<SourceFiancementComponent>;
    let service: SourceFiancementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SourceFiancementComponent],
      })
        .overrideTemplate(SourceFiancementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SourceFiancementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SourceFiancementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SourceFiancement(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sourceFiancements && comp.sourceFiancements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
