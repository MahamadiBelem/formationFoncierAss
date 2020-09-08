import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ConditionAccessDetailComponent } from 'app/entities/condition-access/condition-access-detail.component';
import { ConditionAccess } from 'app/shared/model/condition-access.model';

describe('Component Tests', () => {
  describe('ConditionAccess Management Detail Component', () => {
    let comp: ConditionAccessDetailComponent;
    let fixture: ComponentFixture<ConditionAccessDetailComponent>;
    const route = ({ data: of({ conditionAccess: new ConditionAccess(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ConditionAccessDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConditionAccessDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConditionAccessDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load conditionAccess on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.conditionAccess).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
