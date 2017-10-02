import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class AuthService {

  private readonly CLIENT_ID: string = 'admin';
  private readonly SECRET: string = 'secret';

  constructor(private _httpClient: HttpClient, private _router: Router) { }

  public signIn(userName: string, pwd: string): Promise<any> {

    const body = `username=${userName}&password=${pwd}`;
    const url: string = `${environment.urlApi}/oauth/token?grant_type=password`;
    return this._httpClient.post(url, body, { headers: new HttpHeaders({ 'Accept': 'application/json',
                                                                  'Authorization': `Basic ${btoa(`${this.CLIENT_ID}:${this.SECRET}`)}`,
                                                                  'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' }) })
                          .toPromise().then((result: any) => {
                            sessionStorage.setItem('access_token', result.access_token);
                            sessionStorage.setItem('refresh_token', result.refresh_token);
                            this._router.navigate(['/pages/dashboard']);
                          });
  }

  public getAccessToken(): string {
    return sessionStorage.getItem('access_token');
  }

  public getRefreshToken(): string {
    return sessionStorage.getItem('refresh_token');
  }

  public isAuthenticated() {
    return !!this.getAccessToken();
  }

  public logout(): void {
    sessionStorage.removeItem('access_token');
    sessionStorage.removeItem('refresh_token');
    this._router.navigate(['/login']);
  }

}
