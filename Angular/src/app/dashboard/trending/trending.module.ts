import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrendingRoutingModule } from './trending-routing.module';
import { TrendingComponent } from './trending.component';

@NgModule({
  imports: [
    CommonModule,
    TrendingRoutingModule
  ],
  declarations: [TrendingComponent]
})
export class TrendingModule { }
