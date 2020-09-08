import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { InfrastructureDetailComponent } from 'app/entities/infrastructure/infrastructure-detail.component';
import { Infrastructure } from 'app/shared/model/infrastructure.model';

describe('Component Tests', () => {
  describe('Infrastructure Management Detail Component', () => {
    let comp: InfrastructureDetailComponent;
    let fixture: ComponentFixture<InfrastructureDetailComponent>;
    const route = ({ data: of({ infrastructure: new Infrastructure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [InfrastructureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InfrastructureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InfrastructureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load infrastructure on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.infrastructure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
