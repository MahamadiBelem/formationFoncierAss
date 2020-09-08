import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { FormationsDetailComponent } from 'app/entities/formations/formations-detail.component';
import { Formations } from 'app/shared/model/formations.model';

describe('Component Tests', () => {
  describe('Formations Management Detail Component', () => {
    let comp: FormationsDetailComponent;
    let fixture: ComponentFixture<FormationsDetailComponent>;
    const route = ({ data: of({ formations: new Formations(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [FormationsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormationsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormationsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formations on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formations).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
