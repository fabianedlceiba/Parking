import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { CarPark } from '../components';


declare var $: any;

@Component({
  selector: 'app-car-park',
  templateUrl: './car-park.component.html',
})

export class CarParkComponent implements OnInit, OnDestroy {

  private _url: string;
  private _currentVehicle: CarPark;
  private _selectedVehicle: CarPark;
  private _amountToPaid: number;

  constructor(private _http: HttpClient) {
    this._currentVehicle = new CarPark();
    this._url = environment.api + '/vehicles/park';
  }

  ngOnInit(): void {
  }

  public get currentVehicle(): CarPark {
    return this._currentVehicle;
  }

  public set currentVehicle(currentVehicle: CarPark) {
    this._currentVehicle = currentVehicle;
  }

  public get amountToPaid(){
    return this._amountToPaid;
  }

  public set amountToPaid(amountToPaid: number) {
    this._amountToPaid = amountToPaid;
  }

  public park() {
    this._http.post<CarPark>(this._url, this._currentVehicle)
      .subscribe(carPark => {
        this._selectedVehicle.copy(carPark);
        $('#cardParkModal').modal("hide");
        this._currentVehicle = new CarPark();
      });
  }

  public ngOnDestroy(): void {
    $("body>#cardParkModal").remove();
    $("body>#unparkModal").remove();
  }

  public onSelectedCar(selectedCar: CarPark) {
    this._selectedVehicle = selectedCar;
    this._currentVehicle.vehicle.type = selectedCar.vehicle.type;
    this._currentVehicle.slotNumber = selectedCar.slotNumber;

    if (this._selectedVehicle.available) {
      $('#cardParkModal').appendTo("body").modal();
    }
    else {
      const url = environment.api + '/vehicles/' + selectedCar.vehicle.plate + "/park";
      this._http.put<number>(url, "").subscribe(amount => {
        this._amountToPaid = amount;
        selectedCar.restore();
        $('#unparkModal').appendTo("body").modal();
      });
    } 
  }

}