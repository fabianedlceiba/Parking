import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from "@angular/platform-browser";
import { DebugElement } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


import { CarParkComponent } from './car-park.component';
import { VehicleParkComponent, CardVehicleComponent } from '../components';

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

  it('ee', () => {
    //httpMock.expectOne('http://localhost:8090/api/vehicles/park');
  });

});