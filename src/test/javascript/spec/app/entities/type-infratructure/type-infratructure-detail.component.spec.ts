import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeInfratructureDetailComponent } from 'app/entities/type-infratructure/type-infratructure-detail.component';
import { TypeInfratructure } from 'app/shared/model/type-infratructure.model';

describe('Component Tests', () => {
  describe('TypeInfratructure Management Detail Component', () => {
    let comp: TypeInfratructureDetailComponent;
    let fixture: ComponentFixture<TypeInfratructureDetailComponent>;
    const route = ({ data: of({ typeInfratructure: new TypeInfratructure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeInfratructureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeInfratructureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeInfratructureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeInfratructure on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeInfratructure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
