import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ActiviteInstallationDetailComponent } from 'app/entities/activite-installation/activite-installation-detail.component';
import { ActiviteInstallation } from 'app/shared/model/activite-installation.model';

describe('Component Tests', () => {
  describe('ActiviteInstallation Management Detail Component', () => {
    let comp: ActiviteInstallationDetailComponent;
    let fixture: ComponentFixture<ActiviteInstallationDetailComponent>;
    const route = ({ data: of({ activiteInstallation: new ActiviteInstallation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ActiviteInstallationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ActiviteInstallationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ActiviteInstallationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load activiteInstallation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.activiteInstallation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
