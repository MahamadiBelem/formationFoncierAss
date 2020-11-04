import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionFormationTestModule } from '../../../test.module';
import { ProfilPersonnelDetailComponent } from 'app/entities/profil-personnel/profil-personnel-detail.component';
import { ProfilPersonnel } from 'app/shared/model/profil-personnel.model';

describe('Component Tests', () => {
  describe('ProfilPersonnel Management Detail Component', () => {
    let comp: ProfilPersonnelDetailComponent;
    let fixture: ComponentFixture<ProfilPersonnelDetailComponent>;
    const route = ({ data: of({ profilPersonnel: new ProfilPersonnel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionFormationTestModule],
        declarations: [ProfilPersonnelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProfilPersonnelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProfilPersonnelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load profilPersonnel on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.profilPersonnel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
