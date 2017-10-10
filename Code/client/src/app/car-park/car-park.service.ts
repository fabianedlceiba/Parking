import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/Observable";

import { CarPark } from '../components';
import { environment } from '../../environments/environment';

@Injectable()
export class CarParkService {

  private _url: string;

  constructor(private _http: HttpClient) {
    this._url = `${environment.api}/vehicles/park`;
  }

  public getParkedVehicles(): Observable<Array<CarPark>> {
    return this._http.get<Array<CarPark>>(this._url);
  }

  public park(carPark: CarPark): Observable<CarPark> {
    return this._http.post<CarPark>(this._url, carPark);
  }

  public unpark(plate: string): Observable<number> {
    return this._http.put<number>(`${environment.api}/vehicles/${plate}/park`, '');
  }

}/* istanbul ignore next */