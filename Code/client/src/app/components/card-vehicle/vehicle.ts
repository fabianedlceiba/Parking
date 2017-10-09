export class Vehicle {

  public plate: string;
  public cylinder: number;
  public type: number;

  constructor() { }

  public copy(vehicle: Vehicle) {
    this.plate = vehicle.plate;
    this.cylinder = vehicle.cylinder;
    this.type = vehicle.type;
  }

}