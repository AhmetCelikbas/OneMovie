import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { PageNotFoundComponent } from './not-found.component';

import {  MdToolbarModule, MdSidenavModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MdToolbarModule,
    MdSidenavModule,
    FormsModule
  ],
  declarations: [
    DashboardComponent,
    PageNotFoundComponent
  ]
})
export class DashboardModule { }
