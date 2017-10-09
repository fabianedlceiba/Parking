import { Vehicle } from './vehicle';

export class CarPark {

  public vehicle: Vehicle;
  public entryDate: string;
  public slotNumber: number;
  public notes: string;

  constructor() {
    this.vehicle = new Vehicle();
  }

  public get available(): boolean {
    const plate = this.vehicle.plate || '';
    return plate.length < 6;
  }


  public copy(carPark: CarPark) {
    this.vehicle.copy(carPark.vehicle);
    this.entryDate = carPark.entryDate;
    this.slotNumber = carPark.slotNumber;
    this.notes = carPark.notes;
  }

  public restore(): void {
    this.vehicle.plate = null;
    this.vehicle.cylinder = null;
    this.entryDate = null;
    this.notes = null;
  }

  /*public get vehicle(): Vehicle {
    return this._vehicle;
  }

  public set vehicle(vehicle: Vehicle) {
    this._vehicle = vehicle;
  }

  public get entryDate(): string {
    return this._entryDate;
  }

  public set entryDate(entryDate: string) {
    this._entryDate = entryDate;
  }

  public get exitDate(): string {
    return this._exitDate;
  }

  public set exitDate(exitDate: string) {
    this._entryDate = exitDate;
  }

  public get slotNumber(): number {
    return this._slotNumber;
  }

  public set slotNumber(slotNumber: number) {
    this._slotNumber = slotNumber;
  }

  public get notes(): string {
    return this._notes;
  }

  public set notes(notes: string) {
    this._notes = notes;
  }*/
}