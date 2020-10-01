import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ApprenantesService } from 'app/entities/apprenantes/apprenantes.service';
import { IApprenantes, Apprenantes } from 'app/shared/model/apprenantes.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';
import { Examen } from 'app/shared/model/enumerations/examen.model';

describe('Service Tests', () => {
  describe('Apprenantes Service', () => {
    let injector: TestBed;
    let service: ApprenantesService;
    let httpMock: HttpTestingController;
    let elemDefault: IApprenantes;
    let expectedResult: IApprenantes | IApprenantes[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ApprenantesService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Apprenantes(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        Sexe.MASCULIN,
        'AAAAAAA',
        Examen.CQP,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateNaissance: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Apprenantes', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateNaissance: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
          },
          returnedFromService
        );

        service.create(new Apprenantes()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Apprenantes', () => {
        const returnedFromService = Object.assign(
          {
            matricule: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            sexe: 'BBBBBB',
            contact: 'BBBBBB',
            typecandidat: 'BBBBBB',
            situationMatrimonial: 'BBBBBB',
            charger: 'BBBBBB',
            localite: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Apprenantes', () => {
        const returnedFromService = Object.assign(
          {
            matricule: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            sexe: 'BBBBBB',
            contact: 'BBBBBB',
            typecandidat: 'BBBBBB',
            situationMatrimonial: 'BBBBBB',
            charger: 'BBBBBB',
            localite: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Apprenantes', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
