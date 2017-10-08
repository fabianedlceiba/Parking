import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { VehicleParkComponent, CarPark } from '../components';


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

  @ViewChild("cars")
  private _cars: VehicleParkComponent;
  @ViewChild('motorcycles')
  private _motorcycles: VehicleParkComponent;

  constructor(private _http: HttpClient) {
    this._currentVehicle = new CarPark();
    this._url = environment.api + '/vehicles/park';
  }

  public ngOnInit(): void {
    this._http.get<Array<CarPark>>(this._url).subscribe(data => {
      this._cars.refresh(data);
      this._motorcycles.refresh(data);
    });
  }

  public get currentVehicle(): CarPark {
    return this._currentVehicle;
  }

  public set currentVehicle(currentVehicle: CarPark) {
    this._currentVehicle = currentVehicle;
  }

  public get amountToPaid() {
    return this._amountToPaid;
  }

  public set amountToPaid(amountToPaid: number) {
    this._amountToPaid = amountToPaid;
  }

  public park(): void {
    this._http.post<CarPark>(this._url, this._currentVehicle)
      .subscribe(carPark => {
        this._selectedVehicle.copy(carPark);
        $('#cardParkModal').modal("hide");
        this._currentVehicle = new CarPark();
        this.showNotification('El vehiculo ha sido parqueado exitosamente.', 'warning');
      });
  }

  public unpark(): void {
    $("#txtAmountTendered").val('');
    $('#unparkModal').modal("hide");
  }

  public ngOnDestroy(): void {
    $("body>#cardParkModal").remove();
    $("body>#unparkModal").remove();
  }

  public onSelectedCar(selectedCar: CarPark): void {
    this._selectedVehicle = selectedCar;

    if (this._selectedVehicle.available) {
      $('#cardParkModal').appendTo("body").modal();
      this._currentVehicle.copy(selectedCar);
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

  public showNotification(message, type): void {
    $.notify({
      icon: "notifications",
      message: message
    },
      {
        type: type,
        timer: 4000,
        placement: {
          from: 'bottom',
          align: 'center'
        }
      });
  }

}