import { Vehicle } from './vehicle';

export class CarPark {

  private _vehicle: Vehicle;
  private _entryDate: string;
  private _exitDate: string;
  private _slotNumber: number;
  private _notes: string;

  constructor() {
    this._vehicle = new Vehicle();
   }

  public get vehicle(): Vehicle {
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
  }
}