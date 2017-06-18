import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TopRatedRoutingModule } from './top-rated-routing.module';
import { TopRatedComponent } from './top-rated.component';

@NgModule({
  imports: [
    CommonModule,
    TopRatedRoutingModule
  ],
  declarations: [TopRatedComponent]
})
export class TopRatedModule { }
