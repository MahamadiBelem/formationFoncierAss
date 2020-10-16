import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { FormateurCentreService } from 'app/entities/formateur-centre/formateur-centre.service';
import { IFormateurCentre, FormateurCentre } from 'app/shared/model/formateur-centre.model';
import { RegimeFormateur } from 'app/shared/model/enumerations/regime-formateur.model';

describe('Service Tests', () => {
  describe('FormateurCentre Service', () => {
    let injector: TestBed;
    let service: FormateurCentreService;
    let httpMock: HttpTestingController;
    let elemDefault: IFormateurCentre;
    let expectedResult: IFormateurCentre | IFormateurCentre[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FormateurCentreService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FormateurCentre(0, currentDate, RegimeFormateur.Vacataire);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateEtablissement: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FormateurCentre', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateEtablissement: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEtablissement: currentDate,
          },
          returnedFromService
        );

        service.create(new FormateurCentre()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FormateurCentre', () => {
        const returnedFromService = Object.assign(
          {
            dateEtablissement: currentDate.format(DATE_FORMAT),
            regimeFormateur: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEtablissement: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FormateurCentre', () => {
        const returnedFromService = Object.assign(
          {
            dateEtablissement: currentDate.format(DATE_FORMAT),
            regimeFormateur: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEtablissement: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FormateurCentre', () => {
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
