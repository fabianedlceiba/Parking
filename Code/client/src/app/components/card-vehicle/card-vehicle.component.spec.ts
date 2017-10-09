import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from "@angular/platform-browser";
import { DebugElement } from '@angular/core';

import { CardVehicleComponent } from './card-vehicle.component';
import { CarPark } from './car-park';
import { Vehicle } from './vehicle';

describe('CardVehicleComponent', () => {
  let component: CardVehicleComponent;
  let fixture: ComponentFixture<CardVehicleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CardVehicleComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardVehicleComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the entry date of the car', () => {
    
    const carPark: CarPark = new CarPark();
    carPark.vehicle = new Vehicle();
    carPark.vehicle.plate = 'RMS34D';
    carPark.vehicle.cylinder = 100;
    carPark.vehicle.type = 1;
    carPark.slotNumber = 1;
    carPark.entryDate = '01-10-2017 12:00 P.M'
    carPark.notes = 'Test';
    component.carPark = carPark;
    fixture.detectChanges();

    const entryDateElement: any = fixture.debugElement.query(By.css('.stats')).nativeElement;

    expect(entryDateElement.textContent).toContain(carPark.entryDate);
  });

});
