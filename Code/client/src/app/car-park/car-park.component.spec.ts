import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { By } from "@angular/platform-browser";
import { DebugElement } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { CarParkComponent } from './car-park.component';
import { VehicleParkComponent, CardVehicleComponent, CarPark, Vehicle } from '../components';

describe('CarParkComponent', () => {

  let component: CarParkComponent;
  let fixture: ComponentFixture<CarParkComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        CarParkComponent,
        VehicleParkComponent,
        CardVehicleComponent
      ],
      imports: [
        FormsModule,
        HttpClientTestingModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarParkComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should mark the vehicles were parked', () => {
    fixture.detectChanges();

    const request = httpMock.expectOne('http://localhost:8090/api/vehicles/park');

    const carPlate: string = 'RTD45E';
    const car: CarPark = new CarPark();
    car.vehicle = new Vehicle();
    car.vehicle.plate = carPlate;
    car.vehicle.type = 0;
    car.entryDate = '12-10-2017 08:00 A.M'
    car.slotNumber = 1;

    request.flush([car]);

    fixture.detectChanges();

    const parkedCar = fixture.debugElement.query(By.css("#cars app-card-vehicle"));
    expect(parkedCar.query(By.css('h3')).nativeElement.textContent).toEqual(carPlate);
    expect(parkedCar.query(By.css('.card-header')).attributes['data-background-color']).toEqual('red')

    httpMock.verify();
  });

  it('should show notification with error message', () => {

    fixture.detectChanges();
    const request = httpMock.expectOne('http://localhost:8090/api/vehicles/park');

    request.flush('error', {
      status: 500,
      statusText: 'Internal Error'
    });

    httpMock.verify();

  });

  fit('should park motorcycle', fakeAsync(() => {

    const plate: string = 'RTF456';

    fixture.detectChanges();
    tick();

    const cardVehicle = fixture.debugElement.query(By.css("#motorcycles app-card-vehicle")).nativeElement;
    cardVehicle.click();

    fixture.detectChanges();
    tick();

    const txtPlate = fixture.debugElement.query(By.css('#txtPlate')).nativeElement;
    txtPlate.value = plate;
    txtPlate.dispatchEvent(new Event('input'));
    
    const txtCylinder = fixture.debugElement.query(By.css('#txtCylinder')).nativeElement;
    txtCylinder.value = 100;
    txtCylinder.dispatchEvent(new Event('input'));
        
    fixture.detectChanges();
    tick();

    const request = httpMock.expectOne('http://localhost:8090/api/vehicles/park');
    
    request.flush(new CarPark());

    const btnPark = fixture.debugElement.query(By.css('#btnPark')).nativeElement;
    btnPark.click();
    

  }));

});