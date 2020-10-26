import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { SourceFiancementDetailComponent } from 'app/entities/source-fiancement/source-fiancement-detail.component';
import { SourceFiancement } from 'app/shared/model/source-fiancement.model';

describe('Component Tests', () => {
  describe('SourceFiancement Management Detail Component', () => {
    let comp: SourceFiancementDetailComponent;
    let fixture: ComponentFixture<SourceFiancementDetailComponent>;
    const route = ({ data: of({ sourceFiancement: new SourceFiancement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [SourceFiancementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SourceFiancementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SourceFiancementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sourceFiancement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sourceFiancement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
