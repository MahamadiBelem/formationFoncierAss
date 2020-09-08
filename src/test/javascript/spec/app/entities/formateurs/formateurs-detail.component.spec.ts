import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormateursDetailComponent } from 'app/entities/formateurs/formateurs-detail.component';
import { Formateurs } from 'app/shared/model/formateurs.model';

describe('Component Tests', () => {
  describe('Formateurs Management Detail Component', () => {
    let comp: FormateursDetailComponent;
    let fixture: ComponentFixture<FormateursDetailComponent>;
    const route = ({ data: of({ formateurs: new Formateurs(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormateursDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormateursDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormateursDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formateurs on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formateurs).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
