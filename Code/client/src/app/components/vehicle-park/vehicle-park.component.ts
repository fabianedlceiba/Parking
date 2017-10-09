import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { CarPark } from '../card-vehicle/car-park';

@Component({
  selector: 'app-vehicle-park',
  templateUrl: './vehicle-park.component.html',
})

export class VehicleParkComponent implements OnInit {

  private _amount: number;
  private _type: number;
  private _selectedVehicle: EventEmitter<CarPark>;
  private _parkedVehicles: Array<CarPark> = [];

  constructor() {
    this._selectedVehicle = new EventEmitter<CarPark>();

  }

  public ngOnInit(): void {
    this.buildVehicles();
  }

  private buildVehicles() : void {
    for (let _i = 1; _i <= this.amount; _i++) {
      const carPark: CarPark = new CarPark();
      carPark.slotNumber = _i;
      carPark.vehicle.plate = '';
      carPark.vehicle.type = this.type;
      carPark.notes = '';
      this._parkedVehicles.push(carPark);
    }
  }

  public get parkedVehicles(): Array<CarPark> {
    return this._parkedVehicles;
  }

  public get amount(): number {
    return this._amount;
  }

  @Input()
  public set amount(amount: number) {
    this._amount = amount;
  }

  public get type(): number {
    return this._type;
  }

  @Input()
  public set type(type: number) {
    this._type = type;
  }

  @Output()
  public get selectedVehicle(): EventEmitter<CarPark> {
    return this._selectedVehicle;
  }

  public onClickCard(parkedVehicle: CarPark): void {
    this._selectedVehicle.emit(parkedVehicle);
  }

  public refresh(parkedVehicles: Array<CarPark>) : void {
    if (parkedVehicles.length > 0) {
      const items: Array<CarPark> = parkedVehicles.filter(vehicle => vehicle.vehicle.type == this._type);
      for(let parked of this._parkedVehicles) {
        const findItem: CarPark = items.find(item => item.slotNumber == parked.slotNumber);
        if (findItem) {
          parked.copy(findItem);
        }
      }
    }
  }
  
}/* istanbul ignore next */