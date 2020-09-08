import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { InstallationService } from 'app/entities/installation/installation.service';
import { IInstallation, Installation } from 'app/shared/model/installation.model';

describe('Service Tests', () => {
  describe('Installation Service', () => {
    let injector: TestBed;
    let service: InstallationService;
    let httpMock: HttpTestingController;
    let elemDefault: IInstallation;
    let expectedResult: IInstallation | IInstallation[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InstallationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Installation(0, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateIntallation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Installation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateIntallation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateIntallation: currentDate,
          },
          returnedFromService
        );

        service.create(new Installation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Installation', () => {
        const returnedFromService = Object.assign(
          {
            anneesInstallation: 'BBBBBB',
            dateIntallation: currentDate.format(DATE_FORMAT),
            lieuInstallation: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateIntallation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Installation', () => {
        const returnedFromService = Object.assign(
          {
            anneesInstallation: 'BBBBBB',
            dateIntallation: currentDate.format(DATE_FORMAT),
            lieuInstallation: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateIntallation: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Installation', () => {
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
