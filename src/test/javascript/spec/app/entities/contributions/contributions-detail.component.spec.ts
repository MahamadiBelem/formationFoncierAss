import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ContributionsDetailComponent } from 'app/entities/contributions/contributions-detail.component';
import { Contributions } from 'app/shared/model/contributions.model';

describe('Component Tests', () => {
  describe('Contributions Management Detail Component', () => {
    let comp: ContributionsDetailComponent;
    let fixture: ComponentFixture<ContributionsDetailComponent>;
    const route = ({ data: of({ contributions: new Contributions(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ContributionsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ContributionsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContributionsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load contributions on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contributions).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
