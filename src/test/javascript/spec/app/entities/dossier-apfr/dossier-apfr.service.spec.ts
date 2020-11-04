import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DossierApfrService } from 'app/entities/dossier-apfr/dossier-apfr.service';
import { IDossierApfr, DossierApfr } from 'app/shared/model/dossier-apfr.model';
import { TrancheAge } from 'app/shared/model/enumerations/tranche-age.model';

describe('Service Tests', () => {
  describe('DossierApfr Service', () => {
    let injector: TestBed;
    let service: DossierApfrService;
    let httpMock: HttpTestingController;
    let elemDefault: IDossierApfr;
    let expectedResult: IDossierApfr | IDossierApfr[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DossierApfrService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DossierApfr(
        0,
        'AAAAAAA',
        TrancheAge.Vieux,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        0,
        0,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateReceptionCfv: currentDate.format(DATE_FORMAT),
            dateRetourCt: currentDate.format(DATE_FORMAT),
            dateEnregistrement: currentDate.format(DATE_FORMAT),
            dateDepotSt: currentDate.format(DATE_FORMAT),
            dateRetourSt: currentDate.format(DATE_FORMAT),
            dateNotification: currentDate.format(DATE_FORMAT),
            dateSignature: currentDate.format(DATE_FORMAT),
            taxesTotale: currentDate.format(DATE_FORMAT),
            dateConstatation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DossierApfr', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateReceptionCfv: currentDate.format(DATE_FORMAT),
            dateRetourCt: currentDate.format(DATE_FORMAT),
            dateEnregistrement: currentDate.format(DATE_FORMAT),
            dateDepotSt: currentDate.format(DATE_FORMAT),
            dateRetourSt: currentDate.format(DATE_FORMAT),
            dateNotification: currentDate.format(DATE_FORMAT),
            dateSignature: currentDate.format(DATE_FORMAT),
            taxesTotale: currentDate.format(DATE_FORMAT),
            dateConstatation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReceptionCfv: currentDate,
            dateRetourCt: currentDate,
            dateEnregistrement: currentDate,
            dateDepotSt: currentDate,
            dateRetourSt: currentDate,
            dateNotification: currentDate,
            dateSignature: currentDate,
            taxesTotale: currentDate,
            dateConstatation: currentDate,
          },
          returnedFromService
        );

        service.create(new DossierApfr()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DossierApfr', () => {
        const returnedFromService = Object.assign(
          {
            numApfr: 'BBBBBB',
            trancheAge: 'BBBBBB',
            dateReceptionCfv: currentDate.format(DATE_FORMAT),
            dateRetourCt: currentDate.format(DATE_FORMAT),
            dateEnregistrement: currentDate.format(DATE_FORMAT),
            dateDepotSt: currentDate.format(DATE_FORMAT),
            dateRetourSt: currentDate.format(DATE_FORMAT),
            dateNotification: currentDate.format(DATE_FORMAT),
            dateSignature: currentDate.format(DATE_FORMAT),
            taxesTotale: currentDate.format(DATE_FORMAT),
            donCommune: 1,
            superficieSecurise: 1,
            dateConstatation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReceptionCfv: currentDate,
            dateRetourCt: currentDate,
            dateEnregistrement: currentDate,
            dateDepotSt: currentDate,
            dateRetourSt: currentDate,
            dateNotification: currentDate,
            dateSignature: currentDate,
            taxesTotale: currentDate,
            dateConstatation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DossierApfr', () => {
        const returnedFromService = Object.assign(
          {
            numApfr: 'BBBBBB',
            trancheAge: 'BBBBBB',
            dateReceptionCfv: currentDate.format(DATE_FORMAT),
            dateRetourCt: currentDate.format(DATE_FORMAT),
            dateEnregistrement: currentDate.format(DATE_FORMAT),
            dateDepotSt: currentDate.format(DATE_FORMAT),
            dateRetourSt: currentDate.format(DATE_FORMAT),
            dateNotification: currentDate.format(DATE_FORMAT),
            dateSignature: currentDate.format(DATE_FORMAT),
            taxesTotale: currentDate.format(DATE_FORMAT),
            donCommune: 1,
            superficieSecurise: 1,
            dateConstatation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReceptionCfv: currentDate,
            dateRetourCt: currentDate,
            dateEnregistrement: currentDate,
            dateDepotSt: currentDate,
            dateRetourSt: currentDate,
            dateNotification: currentDate,
            dateSignature: currentDate,
            taxesTotale: currentDate,
            dateConstatation: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DossierApfr', () => {
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
