import { async, ComponentFixture, TestBed, fakeAsync, tick, inject } from '@angular/core/testing';
import { By } from "@angular/platform-browser";
import { DebugElement } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs/Rx';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";

import { CarParkComponent } from './car-park.component';
import { VehicleParkComponent, CardVehicleComponent, CarPark, Vehicle } from '../components';
import { CarParkService } from './car-park.service';

describe('CarParkComponent', () => {

  let component: CarParkComponent;
  let http: HttpTestingController;
  let carPark: CarParkService;
  let fixture: ComponentFixture<CarParkComponent>;

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
      ],
      providers: [
        CarParkService
      ]
    }).compileComponents();
  }));



  beforeEach(() => {
    fixture = TestBed.createComponent(CarParkComponent);
    component = fixture.componentInstance;
    http = TestBed.get(HttpTestingController);
    carPark = TestBed.get(CarParkService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should mark the vehicles were parked', () => {

    /*const carParkService: CarParkService = fixture.debugElement.injector.get(CarParkService);

    // Setup spy on the `getQuote` method
    const car: CarPark = new CarPark();
    car.vehicle = new Vehicle();
    car.vehicle.plate = 'RPC456';
    car.vehicle.type = 0;
    car.entryDate = '12-10-2017 08:00 A.M'
    car.slotNumber = 1;
    spyOn(carParkService, 'getParkedVehicles').and.returnValue([car]);

    fixture.detectChanges();*/
    
    fixture.detectChanges();
    
    const carPlate: string = 'RTD45E';
    const car: CarPark = new CarPark();
    car.vehicle = new Vehicle();
    car.vehicle.plate = carPlate;
    car.vehicle.type = 0;
    car.entryDate = '12-10-2017 08:00 A.M'
    car.slotNumber = 1;
    
    http.expectOne('http://localhost:8090/api/vehicles/park').flush([car]);
    
    fixture.detectChanges();
 
    const parkedCar = fixture.debugElement.query(By.css("#cars app-card-vehicle"));
    
    expect(parkedCar.query(By.css('h3')).nativeElement.textContent).toEqual(carPlate);
    expect(parkedCar.query(By.css('.card-header')).attributes['data-background-color']).toEqual('red')
 
    http.verify();
  });
  
   it('should show notification with error message', () => {
  
     fixture.detectChanges();
     const request = http.expectOne('http://localhost:8090/api/vehicles/park');
  
     request.flush('error', {
       status: 500,
       statusText: 'Internal Error'
     });
  
     http.verify();
  
   });
  
   it('should park motorcycle', fakeAsync(() => {
  
    /*const plate: string = 'RTF456';
  
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
  
     const request = http.expectOne({
       url: 'http://localhost:8090/api/vehicles/park',
       method: 'POST'
     });
     
     request.flush(new CarPark());

     fixture.detectChanges();
     tick();
  
     const btnPark = fixture.debugElement.query(By.css('#btnPark')).nativeElement;
     btnPark.click();*/
     
   }));

});