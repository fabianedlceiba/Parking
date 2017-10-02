import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';

import { RestangularModule, Restangular } from 'ngx-restangular';

/*
 * Platform and Environment providers/directives/pipes
 */
import { routing } from './app.routing';

// App is our top level component
import { App } from './app.component';
import { AppState, InternalStateType } from './app.service';
import { GlobalState } from './global.state';
import { NgaModule } from './theme/nga.module';
import { PagesModule } from './pages/pages.module';
import { environment } from '../environments/environment';
import { SharedModule } from './shared/shared.module';
import { AuthService } from './shared/services';

// Application wide providers
const APP_PROVIDERS = [
  AppState,
  GlobalState,
];

export type StoreType = {
  state: InternalStateType,
  restoreInputValues: () => void,
  disposeOldHosts: () => void,
};


export function RestangularConfigFactory (restangularProvider: any, authService: AuthService) {
  restangularProvider.setDefaultHeaders({ 'Accept': 'application/json' });
  restangularProvider.setBaseUrl(environment.urlApi);

// by each request to the server receive a token and update headers with it
  restangularProvider.addFullRequestInterceptor((element, operation, path, url, headers, params) => {
    const bearerToken = authService.getAccessToken;
      
    return {
      headers: Object.assign({}, headers, { Authorization: `Bearer ${bearerToken}` }),
    };
  });

}

/**
 * `AppModule` is the main entry point into Angular2's bootstraping process
 */
@NgModule({
  bootstrap: [App],
  declarations: [
    App,
  ],
  imports: [ // import Angular's modules
    BrowserModule,
    HttpModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgaModule.forRoot(),
    NgbModule.forRoot(),
    PagesModule,
    routing,
    SharedModule,
    RestangularModule.forRoot([AuthService], RestangularConfigFactory),
  ],
  providers: [ // expose our Services and Providers into Angular's dependency injection
    APP_PROVIDERS,
  ],
})

export class AppModule {

  constructor(public appState: AppState) {
  }
}
