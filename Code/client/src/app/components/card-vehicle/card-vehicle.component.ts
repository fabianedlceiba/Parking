import { Component, OnInit, Input } from '@angular/core';

import { CarPark } from './car-park';


@Component({
  selector: 'app-card-vehicle',
  templateUrl: './card-vehicle.component.html',
  styles: ['.card-stats .card-header { cursor: pointer; }']
})

export class CardVehicleComponent implements OnInit {
  
  private _carPark: CarPark;

  ngOnInit(): void {

  }

  public get carPark(): CarPark {
    return this._carPark;
  }

  @Input()
  public set carPark(carPark: CarPark) {
    this._carPark = carPark;
  }
  
}