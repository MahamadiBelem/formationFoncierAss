import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ImmaDomaineService } from 'app/entities/imma-domaine/imma-domaine.service';
import { IImmaDomaine, ImmaDomaine } from 'app/shared/model/imma-domaine.model';

describe('Service Tests', () => {
  describe('ImmaDomaine Service', () => {
    let injector: TestBed;
    let service: ImmaDomaineService;
    let httpMock: HttpTestingController;
    let elemDefault: IImmaDomaine;
    let expectedResult: IImmaDomaine | IImmaDomaine[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ImmaDomaineService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ImmaDomaine(0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ImmaDomaine', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ImmaDomaine()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ImmaDomaine', () => {
        const returnedFromService = Object.assign(
          {
            annee: 1,
            superficeTotInventorie: 1,
            superficieTotImmatricule: 1,
            superficieTotEncadre: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ImmaDomaine', () => {
        const returnedFromService = Object.assign(
          {
            annee: 1,
            superficeTotInventorie: 1,
            superficieTotImmatricule: 1,
            superficieTotEncadre: 1,
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

      it('should delete a ImmaDomaine', () => {
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
