import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypecandidatDetailComponent } from 'app/entities/typecandidat/typecandidat-detail.component';
import { Typecandidat } from 'app/shared/model/typecandidat.model';

describe('Component Tests', () => {
  describe('Typecandidat Management Detail Component', () => {
    let comp: TypecandidatDetailComponent;
    let fixture: ComponentFixture<TypecandidatDetailComponent>;
    const route = ({ data: of({ typecandidat: new Typecandidat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypecandidatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypecandidatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypecandidatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typecandidat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typecandidat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
