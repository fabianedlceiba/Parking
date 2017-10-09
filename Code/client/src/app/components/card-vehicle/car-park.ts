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

}