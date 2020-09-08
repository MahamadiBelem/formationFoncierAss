import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SortiePromotionService } from 'app/entities/sortie-promotion/sortie-promotion.service';
import { ISortiePromotion, SortiePromotion } from 'app/shared/model/sortie-promotion.model';

describe('Service Tests', () => {
  describe('SortiePromotion Service', () => {
    let injector: TestBed;
    let service: SortiePromotionService;
    let httpMock: HttpTestingController;
    let elemDefault: ISortiePromotion;
    let expectedResult: ISortiePromotion | ISortiePromotion[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SortiePromotionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SortiePromotion(0, currentDate, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateSortie: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SortiePromotion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateSortie: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSortie: currentDate,
          },
          returnedFromService
        );

        service.create(new SortiePromotion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SortiePromotion', () => {
        const returnedFromService = Object.assign(
          {
            dateSortie: currentDate.format(DATE_FORMAT),
            anneesSortie: 1,
            motif: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSortie: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SortiePromotion', () => {
        const returnedFromService = Object.assign(
          {
            dateSortie: currentDate.format(DATE_FORMAT),
            anneesSortie: 1,
            motif: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSortie: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SortiePromotion', () => {
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
