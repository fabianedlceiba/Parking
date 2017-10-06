import { Component, OnInit } from '@angular/core';

import { CarPark } from '../components';

@Component({
  selector: 'app-car-park',
  templateUrl: './car-park.component.html',
})

export class CarParkComponent implements OnInit {

  private _parkedVehicles: Array<CarPark> = [];

  constructor() {

    for (let _i = 0; _i < 10; _i++) {
      const carPark: CarPark = new CarPark();
      carPark.slotNumber = _i;
      carPark.vehicle.plate = '------';
      this._parkedVehicles.push(carPark);
    }

  }

  ngOnInit(): void {

  }

  public get parkedVehicles(): Array<CarPark> {
    return this._parkedVehicles;
  }

  public set parkedVehicles(parkedVehicles: Array<CarPark>) {
    this._parkedVehicles = parkedVehicles;
  }

}