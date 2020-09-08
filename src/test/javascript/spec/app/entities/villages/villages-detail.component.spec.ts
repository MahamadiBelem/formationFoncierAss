import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { VillagesDetailComponent } from 'app/entities/villages/villages-detail.component';
import { Villages } from 'app/shared/model/villages.model';

describe('Component Tests', () => {
  describe('Villages Management Detail Component', () => {
    let comp: VillagesDetailComponent;
    let fixture: ComponentFixture<VillagesDetailComponent>;
    const route = ({ data: of({ villages: new Villages(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [VillagesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VillagesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VillagesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load villages on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.villages).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
