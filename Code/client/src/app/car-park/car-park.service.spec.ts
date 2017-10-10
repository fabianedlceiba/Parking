import { async, ComponentFixture, TestBed, fakeAsync, tick, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpRequest } from '@angular/common/http';

import { CarParkService } from './car-park.service';
import { CarPark, Vehicle } from '../components';

describe('CarParkService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        CarParkService
      ]
    });
  });

  afterEach(inject([HttpTestingController], (httpMock: HttpTestingController) => {
    httpMock.verify();
  }));

  it('should list parked vehicles', inject([HttpTestingController, CarParkService], (httpMock: HttpTestingController, carParkService: CarParkService) => {

    // fake response
    const expectedCar: CarPark = new CarPark();
    expectedCar.vehicle = new Vehicle();
    expectedCar.vehicle.plate = 'RTD45E';
    expectedCar.vehicle.type = 0;
    expectedCar.entryDate = '12-10-2017 08:00 A.M'
    expectedCar.slotNumber = 1;

    const expectedVehicles = [expectedCar];
    carParkService.getParkedVehicles().subscribe(parkedVehicles => expect(parkedVehicles).toEqual(expectedVehicles));

    const req = httpMock.expectOne('http://localhost:8090/api/vehicles/park');

    expect(req.request.method).toEqual('GET');

    req.flush(expectedVehicles)

    httpMock.verify();

  }));

  it('should park a vehicle', inject([HttpTestingController, CarParkService], (httpMock: HttpTestingController, carParkService: CarParkService) => {

    // fake response
    const carPlate: string = 'RTD45E';
    const expectedCar: CarPark = new CarPark();
    expectedCar.vehicle = new Vehicle();
    expectedCar.vehicle.plate = carPlate;
    expectedCar.vehicle.type = 0;
    expectedCar.entryDate = '12-10-2017 08:00 A.M'
    expectedCar.slotNumber = 1;

    carParkService.park(expectedCar).subscribe(vehicle => expect(vehicle).toEqual(expectedCar));

    const req = httpMock.expectOne({ url: 'http://localhost:8090/api/vehicles/park', method: 'POST' });

    expect(req.request.method).toEqual('POST');

    req.flush(expectedCar);

    httpMock.verify();

  }));

  it('should unpark a vehicle', inject([HttpTestingController, CarParkService], (httpMock: HttpTestingController, carParkService: CarParkService) => {

    const plate: string = 'RMT545';
    const total: number = 1000;

    carParkService.unpark(plate).subscribe(result => expect(result).toEqual(total));

    const req = httpMock.expectOne({ url: `http://localhost:8090/api/vehicles/${plate}/park`, method: 'PUT' });

    expect(req.request.method).toEqual('PUT');
    req.flush(total)

    httpMock.verify();

  }));
  
});