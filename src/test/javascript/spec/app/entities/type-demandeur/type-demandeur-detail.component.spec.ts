import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeDemandeurDetailComponent } from 'app/entities/type-demandeur/type-demandeur-detail.component';
import { TypeDemandeur } from 'app/shared/model/type-demandeur.model';

describe('Component Tests', () => {
  describe('TypeDemandeur Management Detail Component', () => {
    let comp: TypeDemandeurDetailComponent;
    let fixture: ComponentFixture<TypeDemandeurDetailComponent>;
    const route = ({ data: of({ typeDemandeur: new TypeDemandeur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeDemandeurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeDemandeurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeDemandeurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeDemandeur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeDemandeur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
