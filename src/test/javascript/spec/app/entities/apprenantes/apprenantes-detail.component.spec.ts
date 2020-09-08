import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ApprenantesDetailComponent } from 'app/entities/apprenantes/apprenantes-detail.component';
import { Apprenantes } from 'app/shared/model/apprenantes.model';

describe('Component Tests', () => {
  describe('Apprenantes Management Detail Component', () => {
    let comp: ApprenantesDetailComponent;
    let fixture: ComponentFixture<ApprenantesDetailComponent>;
    const route = ({ data: of({ apprenantes: new Apprenantes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ApprenantesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ApprenantesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApprenantesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load apprenantes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apprenantes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
