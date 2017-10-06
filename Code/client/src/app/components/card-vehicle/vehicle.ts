export class Vehicle {

  private _plate: string;
  private _cylinder: number;
  private _type: number;

  constructor() { }

  public get plate(): string {
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
  }

}