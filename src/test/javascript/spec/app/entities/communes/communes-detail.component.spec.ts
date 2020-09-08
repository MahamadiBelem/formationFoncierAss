import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CommunesDetailComponent } from 'app/entities/communes/communes-detail.component';
import { Communes } from 'app/shared/model/communes.model';

describe('Component Tests', () => {
  describe('Communes Management Detail Component', () => {
    let comp: CommunesDetailComponent;
    let fixture: ComponentFixture<CommunesDetailComponent>;
    const route = ({ data: of({ communes: new Communes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CommunesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommunesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommunesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load communes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.communes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
