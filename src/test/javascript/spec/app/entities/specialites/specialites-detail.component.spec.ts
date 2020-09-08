import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SpecialitesDetailComponent } from 'app/entities/specialites/specialites-detail.component';
import { Specialites } from 'app/shared/model/specialites.model';

describe('Component Tests', () => {
  describe('Specialites Management Detail Component', () => {
    let comp: SpecialitesDetailComponent;
    let fixture: ComponentFixture<SpecialitesDetailComponent>;
    const route = ({ data: of({ specialites: new Specialites(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SpecialitesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SpecialitesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecialitesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load specialites on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specialites).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
