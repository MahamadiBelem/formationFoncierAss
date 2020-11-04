import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { TypeActeDetailComponent } from 'app/entities/type-acte/type-acte-detail.component';
import { TypeActe } from 'app/shared/model/type-acte.model';

describe('Component Tests', () => {
  describe('TypeActe Management Detail Component', () => {
    let comp: TypeActeDetailComponent;
    let fixture: ComponentFixture<TypeActeDetailComponent>;
    const route = ({ data: of({ typeActe: new TypeActe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [TypeActeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeActeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeActeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeActe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeActe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
