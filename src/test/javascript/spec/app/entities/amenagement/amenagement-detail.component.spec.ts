import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { AmenagementDetailComponent } from 'app/entities/amenagement/amenagement-detail.component';
import { Amenagement } from 'app/shared/model/amenagement.model';

describe('Component Tests', () => {
  describe('Amenagement Management Detail Component', () => {
    let comp: AmenagementDetailComponent;
    let fixture: ComponentFixture<AmenagementDetailComponent>;
    const route = ({ data: of({ amenagement: new Amenagement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [AmenagementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AmenagementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AmenagementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load amenagement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.amenagement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
