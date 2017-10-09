export class Vehicle {

  public plate: string;
  public cylinder: number;
  /*0. Car, 1. Motorcycle */
  public type: number;

  constructor() { }

  public copy(vehicle: Vehicle) {
    this.plate = vehicle.plate;
    this.cylinder = vehicle.cylinder;
    this.type = vehicle.type;
  }

}