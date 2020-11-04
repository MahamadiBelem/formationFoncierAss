import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CaracteristiqueSfrService } from 'app/entities/caracteristique-sfr/caracteristique-sfr.service';
import { ICaracteristiqueSfr, CaracteristiqueSfr } from 'app/shared/model/caracteristique-sfr.model';

describe('Service Tests', () => {
  describe('CaracteristiqueSfr Service', () => {
    let injector: TestBed;
    let service: CaracteristiqueSfrService;
    let httpMock: HttpTestingController;
    let elemDefault: ICaracteristiqueSfr;
    let expectedResult: ICaracteristiqueSfr | ICaracteristiqueSfr[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CaracteristiqueSfrService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CaracteristiqueSfr(0, 0, 0, false, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CaracteristiqueSfr', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CaracteristiqueSfr()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CaracteristiqueSfr', () => {
        const returnedFromService = Object.assign(
          {
            annee: 1,
            nbrAgent: 1,
            equipementInformatique: true,
            equipementCartographique: true,
            accesInternet: true,
            equipemementVehicule: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CaracteristiqueSfr', () => {
        const returnedFromService = Object.assign(
          {
            annee: 1,
            nbrAgent: 1,
            equipementInformatique: true,
            equipementCartographique: true,
            accesInternet: true,
            equipemementVehicule: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CaracteristiqueSfr', () => {
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
