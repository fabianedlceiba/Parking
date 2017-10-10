import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { VehicleParkComponent, CarPark } from '../components';
import { CarParkService } from './car-park.service';

declare var $: any;

@Component({
  selector: 'app-car-park',
  templateUrl: './car-park.component.html',
})

export class CarParkComponent implements OnInit, OnDestroy {

  private _currentVehicle: CarPark;
  private _selectedVehicle: CarPark;
  private _amountToPaid: number;

  @ViewChild("cars")
  private _cars: VehicleParkComponent;

  @ViewChild('motorcycles')
  private _motorcycles: VehicleParkComponent;

  constructor(private _carParkService: CarParkService) {
    this._currentVehicle = new CarPark();
  }

  public ngOnInit(): void {
    this._carParkService.getParkedVehicles().subscribe(data => {
      this._cars.refresh(data);
      this._motorcycles.refresh(data);
    },
      (err: HttpErrorResponse) => this.showNotification(err.error.message || err.statusText, 'danger'));
  }

  public get currentVehicle(): CarPark {
    return this._currentVehicle;
  }

  public get amountToPaid() {
    return this._amountToPaid;
  }

  public set amountToPaid(amountToPaid: number) {
    this._amountToPaid = amountToPaid;
  }

  public park(): void {
    this._carParkService.park(this._currentVehicle)
      .subscribe(carPark => {
        this._selectedVehicle.copy(carPark);
        $('#cardParkModal').modal("hide");
        this._currentVehicle = new CarPark();
        this.showNotification('El vehiculo ha sido parqueado exitosamente.', 'warning');
      },
      (err: HttpErrorResponse) => {
        $('#cardParkModal').modal("hide");
        this.showNotification(err.error.message || err.statusText, 'danger');
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
      this._carParkService.unpark(selectedCar.vehicle.plate).subscribe(amount => {
        this._amountToPaid = amount;
        selectedCar.restore();
        $('#unparkModal').appendTo("body").modal();
      },
        (err: HttpErrorResponse) => {
          this.showNotification(err.error.message || err.statusText, 'danger');
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

}/* istanbul ignore next */