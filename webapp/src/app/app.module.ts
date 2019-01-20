import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';


import { AppComponent } from './app.component';
import { LoginComponent} from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AerogeradorComponent } from './home/aerogerador/aerogerador.component'
import { ParqueEolicoComponent } from './home/parque-eolico/parque-eolico.component'
import { ComplexoEolicoComponent } from './home/complexo-eolico/complexo-eolico.component'

import { JwtInterceptor } from './_helpers/jwt.interceptor'
import { ErrorInterceptor } from './_helpers/error.interceptor';
import { AerogeradorFormComponent } from './home/aerogerador/aerogerador-form/aerogerador-form.component';



@NgModule({
  declarations: [
    AppComponent,   
    LoginComponent,
    HomeComponent,
    AerogeradorComponent,
    ParqueEolicoComponent,
    ComplexoEolicoComponent,
    AerogeradorFormComponent,    

  ],  
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule

  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi:true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi:true },

  ],

  bootstrap: [AppComponent]

})


export class AppModule { }
