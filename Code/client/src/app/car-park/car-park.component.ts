import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';

import { CarPark } from '../components';


declare var $: any;

@Component({
  selector: 'app-car-park',
  templateUrl: './car-park.component.html',
})

export class CarParkComponent implements OnInit, OnDestroy {

  private _parkedVehicles: Array<CarPark> = [];
  private _currentVehicle: CarPark;

  constructor() {

    for (let _i = 1; _i <= 10; _i++) {
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

  public onClickCard(parkedVehicle: CarPark): void {
    this._currentVehicle = parkedVehicle;
    $("#cardParkModal").appendTo("body").modal();
 
    console.info(parkedVehicle);
    
  }

  public onClickSave() {
    this._currentVehicle.vehicle.plate = 'RRR443';
    $("#cardParkModal").modal("hide");
    this._currentVehicle = null;
  }

  public ngOnDestroy(): void {
    $("body>#cardParkModal").remove();
  }

}