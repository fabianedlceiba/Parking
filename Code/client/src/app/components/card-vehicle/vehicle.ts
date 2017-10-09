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

 /* public get plate(): string {
    return this._plate;
  }

  public set plate(plate: string) {
    this._plate = plate;
  }

  public get cylinder(): number {
    return this._cylinder;
  }

  public set cylinder(cylinder: number) {
    this._cylinder = cylinder;
  }

  public get type(): number {
    return this._type;
  }

  public set type(type: number) {
    this._type = type;
  }*/

}