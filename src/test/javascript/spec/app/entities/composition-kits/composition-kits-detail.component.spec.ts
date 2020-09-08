import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { CompositionKitsDetailComponent } from 'app/entities/composition-kits/composition-kits-detail.component';
import { CompositionKits } from 'app/shared/model/composition-kits.model';

describe('Component Tests', () => {
  describe('CompositionKits Management Detail Component', () => {
    let comp: CompositionKitsDetailComponent;
    let fixture: ComponentFixture<CompositionKitsDetailComponent>;
    const route = ({ data: of({ compositionKits: new CompositionKits(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [CompositionKitsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CompositionKitsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompositionKitsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load compositionKits on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.compositionKits).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
