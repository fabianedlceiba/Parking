import { NgModule } from '@angular/core';
import { AuthService } from './services';
import { HttpClientModule } from '@angular/common/http';

const SERVICES = [
  AuthService,
];

const MODULES = [
  HttpClientModule,
];

@NgModule({
  imports: [
    ...MODULES,
  ],
  providers: [
    ...SERVICES,
  ],
})

export class SharedModule { }
