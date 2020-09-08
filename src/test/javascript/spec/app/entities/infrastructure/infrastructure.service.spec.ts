import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { InfrastructureService } from 'app/entities/infrastructure/infrastructure.service';
import { IInfrastructure, Infrastructure } from 'app/shared/model/infrastructure.model';

describe('Service Tests', () => {
  describe('Infrastructure Service', () => {
    let injector: TestBed;
    let service: InfrastructureService;
    let httpMock: HttpTestingController;
    let elemDefault: IInfrastructure;
    let expectedResult: IInfrastructure | IInfrastructure[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InfrastructureService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Infrastructure(0, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateElaboration: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Infrastructure', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateElaboration: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateElaboration: currentDate,
          },
          returnedFromService
        );

        service.create(new Infrastructure()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Infrastructure', () => {
        const returnedFromService = Object.assign(
          {
            dateElaboration: currentDate.format(DATE_FORMAT),
            fonctionnalite: 'BBBBBB',
            etat: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateElaboration: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Infrastructure', () => {
        const returnedFromService = Object.assign(
          {
            dateElaboration: currentDate.format(DATE_FORMAT),
            fonctionnalite: 'BBBBBB',
            etat: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateElaboration: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Infrastructure', () => {
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
