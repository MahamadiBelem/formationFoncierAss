import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ImmaDomaineDetailComponent } from 'app/entities/imma-domaine/imma-domaine-detail.component';
import { ImmaDomaine } from 'app/shared/model/imma-domaine.model';

describe('Component Tests', () => {
  describe('ImmaDomaine Management Detail Component', () => {
    let comp: ImmaDomaineDetailComponent;
    let fixture: ComponentFixture<ImmaDomaineDetailComponent>;
    const route = ({ data: of({ immaDomaine: new ImmaDomaine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ImmaDomaineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ImmaDomaineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ImmaDomaineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load immaDomaine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.immaDomaine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
