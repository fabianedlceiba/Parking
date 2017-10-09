import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from "@angular/platform-browser";

import { CardVehicleComponent } from './card-vehicle.component';
import { CarPark } from './car-park';
import { DebugElement } from '@angular/core';

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
    //fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create 2', () => {
    
    const carPark: CarPark = new CarPark();
    carPark.entryDate = '01-10-2017 12:00 P.M'
    component.carPark = carPark;
    fixture.detectChanges();

    const entryDateElement: any = fixture.debugElement.query(By.css('.stats')).nativeElement;

    expect(entryDateElement.textContent).toContain(carPark.entryDate);
  });

});
