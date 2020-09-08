import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ProvincesDetailComponent } from 'app/entities/provinces/provinces-detail.component';
import { Provinces } from 'app/shared/model/provinces.model';

describe('Component Tests', () => {
  describe('Provinces Management Detail Component', () => {
    let comp: ProvincesDetailComponent;
    let fixture: ComponentFixture<ProvincesDetailComponent>;
    const route = ({ data: of({ provinces: new Provinces(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ProvincesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProvincesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProvincesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load provinces on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.provinces).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
