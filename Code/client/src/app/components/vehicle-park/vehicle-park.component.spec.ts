import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from "@angular/platform-browser";
import { DebugElement } from '@angular/core';

import { VehicleParkComponent } from './vehicle-park.component';
import { CardVehicleComponent } from '../card-vehicle/card-vehicle.component'
import { CarPark } from '../card-vehicle/car-park';
import { Vehicle } from '../card-vehicle/vehicle';

describe('VehicleParkComponent', () => {
  let component: VehicleParkComponent;
  let fixture: ComponentFixture<VehicleParkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [VehicleParkComponent,
        CardVehicleComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleParkComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display one car', () => {
    component.amount = 1;
    component.type = 1;
    fixture.detectChanges();

    const appCardElements = fixture.debugElement.queryAll(By.css('app-card-vehicle'));
    
    expect(appCardElements.length).toEqual(1);
  });

  
  it('should mark vehicle card component as busy', () => {
    component.amount = 1;
    component.type = 1;
    fixture.detectChanges();

    const cars: Array<CarPark> = [];
    const car: CarPark = new CarPark();
    car.vehicle = new Vehicle();
    car.vehicle.plate = 'RMS34D';
    car.vehicle.type = 1;
    car.slotNumber = 1;
    cars.push(car);
    component.refresh(cars);
    fixture.detectChanges();

    const appCardElements = fixture.debugElement.query(By.css('app-card-vehicle .card-header'));
    
    expect(appCardElements.attributes['data-background-color']).toEqual('red');
  });

  it('should mark anything component', () => {
    component.amount = 1;
    component.type = 1;
    fixture.detectChanges();

    component.refresh(new Array());
    fixture.detectChanges();

    const appCardElements = fixture.debugElement.query(By.css('app-card-vehicle .card-header'));
   
    expect(appCardElements.attributes['data-background-color']).toEqual('green');
  });

  it('should dosent mark vehicle card component', () => {
    
    component.amount = 1;
    component.type = 1;
    fixture.detectChanges();

    const cars: Array<CarPark> = [];
    const car: CarPark = new CarPark();
    car.vehicle = new Vehicle();
    car.vehicle.plate = 'RMS34D';
    car.vehicle.type = 1;
    car.slotNumber = 2;
    cars.push(car);
    component.refresh(cars);
    fixture.detectChanges();

    const appCardElements = fixture.debugElement.query(By.css('app-card-vehicle .card-header'));
    
    expect(appCardElements.attributes['data-background-color']).toEqual('green');
  });


});
