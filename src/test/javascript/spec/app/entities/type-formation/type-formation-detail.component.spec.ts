import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeFormationDetailComponent } from 'app/entities/type-formation/type-formation-detail.component';
import { TypeFormation } from 'app/shared/model/type-formation.model';

describe('Component Tests', () => {
  describe('TypeFormation Management Detail Component', () => {
    let comp: TypeFormationDetailComponent;
    let fixture: ComponentFixture<TypeFormationDetailComponent>;
    const route = ({ data: of({ typeFormation: new TypeFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeFormation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
