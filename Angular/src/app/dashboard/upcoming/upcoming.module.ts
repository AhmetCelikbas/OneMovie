import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UpcomingRoutingModule } from './upcoming-routing.module';
import { UpcomingComponent } from './upcoming.component';

@NgModule({
  imports: [
    CommonModule,
    UpcomingRoutingModule
  ],
  declarations: [UpcomingComponent]
})
export class UpcomingModule { }
