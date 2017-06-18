import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { MdProgressBarModule } from '@angular/material';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import 'hammerjs';

import { ConfigService } from './services/config.service';
import { DataService } from './services/data.service';
import { AuthenticationService } from './services/authentication.service';

import { HammerGestureConfig, HAMMER_GESTURE_CONFIG } from '@angular/platform-browser';

declare var Hammer: any;

export class curstomHammerConfig extends HammerGestureConfig {
    buildHammer(element: HTMLElement) {
        let mc = new Hammer(element, {
            touchAction: "pan-y",
        });
        return mc;
    }
}


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    MdProgressBarModule
  ],
  providers: [
    ConfigService,
    DataService,
    AuthenticationService,
    { provide: HAMMER_GESTURE_CONFIG, useClass: curstomHammerConfig }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
