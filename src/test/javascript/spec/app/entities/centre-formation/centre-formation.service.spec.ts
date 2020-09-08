import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CentreFormationService } from 'app/entities/centre-formation/centre-formation.service';
import { ICentreFormation, CentreFormation } from 'app/shared/model/centre-formation.model';

describe('Service Tests', () => {
  describe('CentreFormation Service', () => {
    let injector: TestBed;
    let service: CentreFormationService;
    let httpMock: HttpTestingController;
    let elemDefault: ICentreFormation;
    let expectedResult: ICentreFormation | ICentreFormation[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CentreFormationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CentreFormation(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateOuverture: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CentreFormation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOuverture: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOuverture: currentDate,
          },
          returnedFromService
        );

        service.create(new CentreFormation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CentreFormation', () => {
        const returnedFromService = Object.assign(
          {
            denomination: 'BBBBBB',
            localisation: 'BBBBBB',
            adresse: 'BBBBBB',
            statuts: 'BBBBBB',
            capaciteacceuil: 'BBBBBB',
            refOuverture: 'BBBBBB',
            dateOuverture: currentDate.format(DATE_FORMAT),
            regime: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOuverture: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CentreFormation', () => {
        const returnedFromService = Object.assign(
          {
            denomination: 'BBBBBB',
            localisation: 'BBBBBB',
            adresse: 'BBBBBB',
            statuts: 'BBBBBB',
            capaciteacceuil: 'BBBBBB',
            refOuverture: 'BBBBBB',
            dateOuverture: currentDate.format(DATE_FORMAT),
            regime: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOuverture: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CentreFormation', () => {
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
