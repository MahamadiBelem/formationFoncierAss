import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeAmenagementDetailComponent } from 'app/entities/type-amenagement/type-amenagement-detail.component';
import { TypeAmenagement } from 'app/shared/model/type-amenagement.model';

describe('Component Tests', () => {
  describe('TypeAmenagement Management Detail Component', () => {
    let comp: TypeAmenagementDetailComponent;
    let fixture: ComponentFixture<TypeAmenagementDetailComponent>;
    const route = ({ data: of({ typeAmenagement: new TypeAmenagement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeAmenagementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeAmenagementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeAmenagementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeAmenagement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeAmenagement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
