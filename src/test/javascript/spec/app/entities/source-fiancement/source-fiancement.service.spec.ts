import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SourceFiancementService } from 'app/entities/source-fiancement/source-fiancement.service';
import { ISourceFiancement, SourceFiancement } from 'app/shared/model/source-fiancement.model';
import { Partenaire } from 'app/shared/model/enumerations/partenaire.model';

describe('Service Tests', () => {
  describe('SourceFiancement Service', () => {
    let injector: TestBed;
    let service: SourceFiancementService;
    let httpMock: HttpTestingController;
    let elemDefault: ISourceFiancement;
    let expectedResult: ISourceFiancement | ISourceFiancement[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SourceFiancementService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SourceFiancement(0, 'AAAAAAA', Partenaire.Etat);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SourceFiancement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SourceFiancement()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SourceFiancement', () => {
        const returnedFromService = Object.assign(
          {
            libelleSource: 'BBBBBB',
            partenaire: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SourceFiancement', () => {
        const returnedFromService = Object.assign(
          {
            libelleSource: 'BBBBBB',
            partenaire: 'BBBBBB',
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

      it('should delete a SourceFiancement', () => {
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
