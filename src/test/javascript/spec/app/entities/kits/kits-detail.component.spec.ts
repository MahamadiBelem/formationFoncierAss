import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { KitsDetailComponent } from 'app/entities/kits/kits-detail.component';
import { Kits } from 'app/shared/model/kits.model';

describe('Component Tests', () => {
  describe('Kits Management Detail Component', () => {
    let comp: KitsDetailComponent;
    let fixture: ComponentFixture<KitsDetailComponent>;
    const route = ({ data: of({ kits: new Kits(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [KitsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(KitsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(KitsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load kits on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.kits).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
