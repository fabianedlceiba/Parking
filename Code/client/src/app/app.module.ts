import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { CarParkComponent } from './car-park/car-park.component';
import { VehicleParkComponent, CardVehicleComponent } from './components';

import { CarParkService } from './car-park/car-park.service';
import { OnlyNumberDirective } from './directives';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CarParkComponent,
    VehicleParkComponent,
    CardVehicleComponent,
    OnlyNumberDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ComponentsModule,
    RouterModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    CarParkService
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
